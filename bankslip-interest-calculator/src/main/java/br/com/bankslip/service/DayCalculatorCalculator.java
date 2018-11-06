package br.com.bankslip.service;

import br.com.bankslip.domain.Bankslip;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
public class DayCalculatorCalculator implements DayCalculator {
    public long getDayDifference(final Bankslip bankslip){
        final LocalDateTime dueDate = bankslip.getDueDate().atStartOfDay();
        final LocalDateTime currentDate = LocalDateTime.now();
        return Duration.between(dueDate, currentDate).toDays();
    }
}
