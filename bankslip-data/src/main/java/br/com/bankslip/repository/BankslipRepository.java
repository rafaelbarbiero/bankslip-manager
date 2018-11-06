package br.com.bankslip.repository;

import br.com.bankslip.domain.Bankslip;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
public interface BankslipRepository extends JpaRepository<Bankslip, UUID> {
}
