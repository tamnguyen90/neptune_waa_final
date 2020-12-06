package edu.miu.cs.neptune.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "bid")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double biddingAmount;

    @Column(name = "bidding_time")
    private LocalDateTime biddingTime;

    public Bid() {}

    public Bid(Double biddingAmount, LocalDateTime biddingTime) {
        this.biddingAmount = biddingAmount;
        this.biddingTime = biddingTime;
    }

    public Long getId() {
        return id;
    }

    public Double getBiddingAmount() {
        return biddingAmount;
    }

    public void setBiddingAmount(Double biddingAmount) {
        this.biddingAmount = biddingAmount;
    }

    public LocalDateTime getBiddingTime() {
        return biddingTime;
    }

    public void setBiddingTime(LocalDateTime biddingTime) {
        this.biddingTime = biddingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return id.equals(bid.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", biddingAmount=" + biddingAmount +
                ", biddingTime=" + biddingTime +
                '}';
    }
}
