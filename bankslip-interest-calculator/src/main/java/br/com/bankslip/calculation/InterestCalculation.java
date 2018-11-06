package br.com.bankslip.calculation;

import br.com.bankslip.domain.Bankslip;
import br.com.bankslip.rules.InterestCalculationRule;

import java.math.BigDecimal;
import java.util.List;

public interface InterestCalculation {

    BigDecimal calculateInterest(final Bankslip bankslip, final List<InterestCalculationRule> interestCalculationRuleList);

}
