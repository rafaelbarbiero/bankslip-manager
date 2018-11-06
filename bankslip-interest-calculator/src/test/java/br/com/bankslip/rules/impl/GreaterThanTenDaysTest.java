package br.com.bankslip.rules.impl;

import br.com.bankslip.BankslipsInterestCalculator;
import br.com.bankslip.domain.Bankslip;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest(classes = BankslipsInterestCalculator.class)
@RunWith(SpringRunner.class)
public class GreaterThanTenDaysTest {

    Bankslip bankslipGreaterThan;
    Bankslip upToDays;

    @Autowired GreaterThanTenDays greaterThanTenDays;

    @Before
    public void setUp() {
        bankslipGreaterThan = new Bankslip(LocalDate.now().minusDays(11), 99000, "Customer Teste");
        upToDays = new Bankslip(LocalDate.now().minusDays(10), 99000, "Customer Teste");
    }

    @Test
    public void shouldAccept() {
        Assertions.assertThat(greaterThanTenDays.accept(bankslipGreaterThan)).isTrue();
    }

    @Test
    public void shouldNotAccept() {
        Assertions.assertThat(greaterThanTenDays.accept(upToDays)).isFalse();
    }

    @Test
    public void calculate() {
        Assertions.assertThat(greaterThanTenDays.calculate(bankslipGreaterThan)).isEqualTo(BigDecimal.valueOf(10890.0));
    }
}