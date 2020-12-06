package edu.miu.cs.neptune.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Auction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionId;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private AuctionStatus auctionStatus;
    private Double beginPrice;
    private Long winnerBidId;
    private List<Bid> bidId;
    private LocalDateTime shippingDate;
    private ShippingStatus shippingStatus;
}
