package br.com.bankslip.calculation.impl;

import br.com.bankslip.BankslipsInterestCalculator;
import br.com.bankslip.calculation.InterestCalculation;
import br.com.bankslip.domain.Bankslip;
import br.com.bankslip.rules.impl.UpToDays;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

@SpringBootTest(classes = BankslipsInterestCalculator.class)
@RunWith(SpringRunner.class)
public class InterestCalculatorTest {

    Bankslip bankslip;

    @Autowired InterestCalculation interestCalculation;
    @Autowired UpToDays upToDays;

    @Before
    public void setUp() {
        bankslip = new Bankslip(LocalDate.now().minusDays(3), 99000, "Customer Teste");
    }

    @Test
    public void calculateInterest() {
        Assertions.assertThat(interestCalculation.calculateInterest(bankslip, Collections.singletonList(upToDays))).isEqualTo(BigDecimal.valueOf(1485.0));
    }
}