package br.com.bankslip.handler;

import br.com.bankslip.domain.Bankslip;
import br.com.bankslip.domain.Payment;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface Handler {

    public ResponseEntity<List<Bankslip>> findAll();

    public ResponseEntity<Bankslip> find(final UUID id);

    public ResponseEntity<Bankslip> create(final Bankslip bankslip);

    public ResponseEntity pay(final UUID id, final Payment payment);

    public ResponseEntity cancel(final UUID id);
}
