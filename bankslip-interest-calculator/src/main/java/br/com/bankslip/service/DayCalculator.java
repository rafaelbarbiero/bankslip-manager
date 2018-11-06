package br.com.bankslip.service;

import br.com.bankslip.domain.Bankslip;

public interface DayCalculator {
    public long getDayDifference(final Bankslip bankslip);
}
