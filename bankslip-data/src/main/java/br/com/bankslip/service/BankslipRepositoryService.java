package br.com.bankslip.service;

import br.com.bankslip.repository.BankslipRepository;
import br.com.bankslip.domain.Bankslip;
import br.com.bankslip.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankslipRepositoryService {

    BankslipRepository bankslipRepository;

    @Autowired
    public BankslipRepositoryService(BankslipRepository bankslipRepository) {
        this.bankslipRepository = bankslipRepository;
    }

    public Bankslip save(final Bankslip bankslip) {
        return bankslipRepository.save(bankslip);
    }

    public List<Bankslip> findAll() {
        return bankslipRepository.findAll();
    }

    public Bankslip findById(final UUID id) {
        return bankslipRepository.findById(id).orElse(null);
    }

    public Bankslip paid(final UUID id, final LocalDate paymentDate) {
        return Optional.ofNullable(this.findById(id))
                .map(bankslip -> {
                    bankslip.setPaymentDate(paymentDate);
                    return this.save(bankslip);
                }).orElse(null);
    }

    public Bankslip cancel(final UUID id) {
        return Optional.ofNullable(this.findById(id))
                .map(bankslip -> {
                    bankslip.setStatus(Status.CANCELED);
                    return this.save(bankslip);
                }).orElse(null);
    }
}
