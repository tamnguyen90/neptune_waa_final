package edu.miu.cs.neptune.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class SystemPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long auctionId;
    private Long userId;

    @NotNull
    private Double paymentAmount;

    private Double balance;

    @NotNull
    private PaymentStatus paymentStatus;

    public SystemPayment() {}

    public SystemPayment(Long auctionId, Long userId, @NotNull Double paymentAmount, @NotNull PaymentStatus paymentStatus) {
        this.auctionId = auctionId;
        this.userId = userId;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Double getBalance() {
        return balance;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public Long getUserId() {
        return userId;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus status) {
        this.paymentStatus = status;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount, PaymentStatus paymentStatus) {
        this.balance -= amount;
        setPaymentStatus(paymentStatus);
    }
}
