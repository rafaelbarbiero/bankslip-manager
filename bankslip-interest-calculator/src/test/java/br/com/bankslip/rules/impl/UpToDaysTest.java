package br.com.bankslip.rules.impl;

import br.com.bankslip.BankslipsInterestCalculator;
import br.com.bankslip.domain.Bankslip;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest(classes = BankslipsInterestCalculator.class)
@RunWith(SpringRunner.class)
public class UpToDaysTest {

    Bankslip bankslipUpTo;
    Bankslip bankslipGreaterThan;

    @Autowired UpToDays UpToDays;

    @Before
    public void setUp() {
        bankslipUpTo = new Bankslip(LocalDate.now().minusDays(3), 99000, "Customer Teste");
        bankslipGreaterThan = new Bankslip(LocalDate.now().minusDays(11), 99000, "Customer Teste");
    }

    @Test
    public void shouldAccept() {
        Assertions.assertThat(UpToDays.accept(bankslipUpTo)).isTrue();
    }

    @Test
    public void shouldNotAccept() {
        Assertions.assertThat(UpToDays.accept(bankslipGreaterThan)).isFalse();
    }

    @Test
    public void calculate() {
        Assertions.assertThat(UpToDays.calculate(bankslipUpTo)).isEqualTo(BigDecimal.valueOf(1485.0));
    }
}