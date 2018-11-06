package br.com.bankslip.service;

import br.com.bankslip.domain.Bankslip;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class DayDifferenceCalculatorTest {

    Bankslip bankslip;
    DayDifferenceCalculator dayDifferenceCalculator;

    @Before
    public void setUp() {
        bankslip = new Bankslip(LocalDate.now().minusDays(20), 99000, "Customer Teste");
        dayDifferenceCalculator = new DayDifferenceCalculator();
    }

    @Test
    public void getDayDifference() {
        Assertions.assertThat(dayDifferenceCalculator.getDayDifference(bankslip)).isEqualTo(20);
    }
}