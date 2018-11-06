package br.com.bankslip.service;

import br.com.bankslip.domain.Bankslip;
import br.com.bankslip.repository.BankslipRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BankslipRepositoryServiceTest {

    @Mock BankslipRepository bankslipRepository;
    BankslipRepositoryService bankslipRepositoryService;
    Bankslip bankslip;
    UUID uuid;
    UUID uuidNotFound;

    @Before
    public void setUp() {
        uuid = UUID.randomUUID();
        uuidNotFound = UUID.randomUUID();
        bankslip = new Bankslip();
        bankslip.setId(uuid);
        bankslipRepository = mock(BankslipRepository.class);
        Mockito.when(bankslipRepository.findById(uuid)).thenReturn(Optional.of(bankslip));
        Mockito.when(bankslipRepository.findById(uuidNotFound)).thenReturn(null);
        Mockito.when(bankslipRepository.save(bankslip)).thenReturn(bankslip);
        Mockito.when(bankslipRepository.findAll()).thenReturn(Collections.singletonList(bankslip));
        bankslipRepositoryService = new BankslipRepositoryService(bankslipRepository);
    }

    @Test
    public void save() {
        Assertions.assertThat(bankslipRepository.save(bankslip)).isEqualTo(bankslip);
    }

    @Test
    public void findAll() {
        Assertions.assertThat(bankslipRepositoryService.findAll())
                .isNotEmpty()
                .isEqualTo(Collections.singletonList(bankslip));
    }

    @Test
    public void findById() {
        Assertions.assertThat(bankslipRepository.findById(uuid)).isEqualTo(Optional.of(bankslip));
    }

    @Test
    public void findByIdNotFound() {
        Assertions.assertThat(bankslipRepository.findById(uuidNotFound)).isNull();
    }

    @Test
    public void paid() {
        Assertions.assertThat(bankslipRepositoryService.paid(uuid, LocalDate.now())).isEqualTo(bankslip);
    }

    @Test
    public void cancel() {
        Assertions.assertThat(bankslipRepositoryService.cancel(uuid)).isEqualTo(bankslip);
    }
}