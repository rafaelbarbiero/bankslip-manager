package br.com.bankslip.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Payment {

    @JsonProperty(value = "payment_date")
    private LocalDate paymentDate;

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
