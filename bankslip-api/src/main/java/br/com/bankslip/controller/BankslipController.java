package br.com.bankslip.controller;

import br.com.bankslip.domain.Bankslip;
import br.com.bankslip.domain.Payment;
import br.com.bankslip.handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/bankslips")
public class BankslipController {

    @Autowired Handler handlerResponse;

    @PostMapping
    public ResponseEntity<Bankslip> create(@RequestBody @Valid Bankslip bankslip) {
        return handlerResponse.create(bankslip);
    }

    @GetMapping
    public ResponseEntity<List<Bankslip>> findAll() {
        return handlerResponse.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bankslip> findById(@PathVariable("id") UUID id) {
        return handlerResponse.find(id);
    }

    @PostMapping("/{id}/payments")
    public ResponseEntity pay(@PathVariable("id") UUID id, @RequestBody Payment payment) {
        return handlerResponse.pay(id, payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity cancel(@PathVariable("id") UUID id) {
        return handlerResponse.cancel(id);
    }

}
