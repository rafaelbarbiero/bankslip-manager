package br.com.bankslip.handler.impl;

import br.com.bankslip.calculation.InterestCalculation;
import br.com.bankslip.domain.Bankslip;
import br.com.bankslip.domain.Payment;
import br.com.bankslip.domain.Status;
import br.com.bankslip.handler.Handler;
import br.com.bankslip.rules.InterestCalculationRule;
import br.com.bankslip.service.BankslipRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ResponseHandler implements Handler {

    @Autowired BankslipRepositoryService bankslipRepositoryService;
    @Autowired List<InterestCalculationRule> interestCalculationRules;
    @Autowired InterestCalculation interestCalculation;

    private static final String BANKSLIP_NOT_FOUND_WITH_THE_SPECIFIED_ID = "Bankslip not found with the specified id";

    public ResponseEntity<List<Bankslip>> findAll() {
        return ResponseEntity.ok(bankslipRepositoryService.findAll());
    }

    public ResponseEntity find(final UUID id) {
        return Optional.ofNullable(bankslipRepositoryService.findById(id))
                .map(bankslip -> {
                    final BigDecimal fine = interestCalculation.calculateInterest(bankslip, interestCalculationRules);
                    bankslip.setFine(fine);
                    return ResponseEntity.status(HttpStatus.OK).body(bankslip);
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Bankslip> create(final Bankslip bankslip) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankslipRepositoryService.save(bankslip));
    }

    public ResponseEntity pay(final UUID id, final Payment payment) {
        return Optional.ofNullable(bankslipRepositoryService.findById(id))
                .map(bankslip -> {
                    bankslip.setPaymentDate(payment.getPaymentDate());
                    bankslip.setStatus(Status.PAID);
                    bankslipRepositoryService.save(bankslip);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(BANKSLIP_NOT_FOUND_WITH_THE_SPECIFIED_ID));
    }

    public ResponseEntity cancel(final UUID id) {
        return Optional.ofNullable(bankslipRepositoryService.findById(id))
                .map(bankslip -> {
                    bankslip.setStatus(Status.CANCELED);
                    bankslipRepositoryService.save(bankslip);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(BANKSLIP_NOT_FOUND_WITH_THE_SPECIFIED_ID));

    }
}
