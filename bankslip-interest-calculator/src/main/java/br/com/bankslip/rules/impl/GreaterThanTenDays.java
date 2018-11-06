package br.com.bankslip.rules.impl;

import br.com.bankslip.domain.Bankslip;
import br.com.bankslip.rules.InterestCalculationRule;
import br.com.bankslip.service.DayCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class GreaterThanTenDays implements InterestCalculationRule {

    @Autowired DayCalculator dayDifferenceCalculator;
    @Value(value = "${interest.daysUpTo}") Integer daysUpTo;
    @Value(value = "${interest.percentsGreaterThan}") Double fine;

    @Override
    public boolean accept(Bankslip bankslip) {
        return LocalDate.now().isAfter(bankslip.getDueDate().plusDays(daysUpTo));
    }

    @Override
    public BigDecimal calculate(Bankslip bankslip) {
        final long dayDifference = dayDifferenceCalculator.getDayDifference(bankslip);
        return BigDecimal.valueOf(((bankslip.getTotalInCents() * fine) * dayDifference) / 100);
    }
}
