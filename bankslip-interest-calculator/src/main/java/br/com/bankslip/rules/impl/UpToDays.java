package br.com.bankslip.rules.impl;

import br.com.bankslip.domain.Bankslip;
import br.com.bankslip.rules.InterestCalculationRule;
import br.com.bankslip.service.DayCalculator;
import br.com.bankslip.service.DayCalculatorCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.*;

@Component
public class UpToDays implements InterestCalculationRule {

    @Autowired DayCalculator dayDifferenceCalculator;
    @Value(value = "${interest.daysUpTo}") Integer daysUpTo;
    @Value(value = "${interest.percentsDaysUpTo}") Double percents;

    @Override
    public boolean accept(final Bankslip bankslip) {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate dueDate = bankslip.getDueDate();
        return dueDate.isBefore(currentDate) && currentDate.isBefore(dueDate.plusDays(daysUpTo));
    }

    @Override
    public BigDecimal calculate(final Bankslip bankslip) {
        final long dayDifference = dayDifferenceCalculator.getDayDifference(bankslip);
        return BigDecimal.valueOf(((bankslip.getTotalInCents() * percents) * dayDifference) / 100);
    }
}
