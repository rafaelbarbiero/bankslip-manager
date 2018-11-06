package br.com.bankslip.calculation.impl;

import br.com.bankslip.calculation.InterestCalculation;
import br.com.bankslip.domain.Bankslip;
import br.com.bankslip.rules.InterestCalculationRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class InterestCalculator implements InterestCalculation {
    @Override
    public BigDecimal calculateInterest(final Bankslip bankslip, final List<InterestCalculationRule> interestCalculationRuleList) {
        return interestCalculationRuleList.stream()
                .filter(interestCalculation -> interestCalculation.accept(bankslip))
                .map(interestCalculation -> interestCalculation.calculate(bankslip))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
