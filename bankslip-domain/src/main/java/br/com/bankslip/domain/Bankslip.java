package br.com.bankslip.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "bankslips")
public class Bankslip {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @NotNull @JsonProperty(value = "due_date")
    private LocalDate dueDate;

    @NotNull @Min(1) @JsonProperty(value = "total_in_cents")
    private Integer totalInCents;

    @NotBlank
    private String customer;

    @JsonProperty(value = "payment_date")
    private LocalDate paymentDate;

    private BigDecimal fine;

    private Status status = Status.PENDING;

    public Bankslip() {
    }

    public Bankslip(@NotNull LocalDate dueDate, @NotNull @Min(1) Integer totalInCents, @NotBlank String customer) {
        this.dueDate = dueDate;
        this.totalInCents = totalInCents;
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getTotalInCents() {
        return totalInCents;
    }

    public void setTotalInCents(Integer totalInCents) {
        this.totalInCents = totalInCents;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bankslip bankslip = (Bankslip) o;
        return Objects.equals(id, bankslip.id) &&
                Objects.equals(totalInCents, bankslip.totalInCents) &&
                Objects.equals(customer, bankslip.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalInCents, customer);
    }
}
