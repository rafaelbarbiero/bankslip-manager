package br.com.bankslip.controller;

import br.com.bankslip.BankslipsAPI;
import br.com.bankslip.domain.Bankslip;
import br.com.bankslip.domain.Payment;
import br.com.bankslip.repository.BankslipRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = BankslipsAPI.class)
@RunWith(SpringRunner.class)
public class BankslipControllerTest {

    @Autowired BankslipController bankslipController;
    @Autowired BankslipRepository bankslipRepository;

    Bankslip bankslip;

    @Before
    public void setUp() {
        bankslip = new Bankslip(LocalDate.now(), 99000, "Customer Test");
    }

    @After
    public void tearDown() {
        bankslipRepository.delete(bankslip);
    }

    @Test
    public void create() {
        final ResponseEntity<Bankslip> responseEntity = bankslipController.create(bankslip);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(bankslip);
    }

    @Test
    public void findAll() {
        bankslipController.create(bankslip);
        final ResponseEntity<List<Bankslip>> responseEntity = bankslipController.findAll();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().size()).isEqualTo(1);
    }

    @Test
    public void findById() {
        bankslipController.create(bankslip);
        final ResponseEntity<Bankslip> responseEntity = bankslipController.findById(bankslip.getId());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(bankslip);
    }

    @Test
    public void findByIdShouldBeNotFound() {
        bankslipController.create(bankslip);
        final ResponseEntity<Bankslip> responseEntity = bankslipController.findById(UUID.randomUUID());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void pay() {
        bankslipController.create(bankslip);
        Payment payment = new Payment();
        payment.setPaymentDate(LocalDate.now());
        final ResponseEntity<?> responseEntity = bankslipController.pay(bankslip.getId(), payment);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void cancel() {
        bankslipController.create(bankslip);
        final ResponseEntity<?> responseEntity = bankslipController.cancel(bankslip.getId());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}