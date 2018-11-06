package br.com.bankslip.rules;

import br.com.bankslip.domain.Bankslip;

import java.math.BigDecimal;

public interface InterestCalculationRule {
    boolean accept(Bankslip bankslip);
    BigDecimal calculate(Bankslip bankslip);
}
