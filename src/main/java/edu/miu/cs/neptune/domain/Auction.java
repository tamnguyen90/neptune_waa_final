package edu.miu.cs.neptune.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Auction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionId;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private AuctionStatus auctionStatus;
    private Double beginPrice;
    private Long winnerBidId;
    @OneToMany
    private List<Bid> bidId;
    private LocalDateTime shippingDate;
    private ShippingStatus shippingStatus;

    @OneToOne(mappedBy = "auction", cascade = CascadeType.ALL)
    private Product product;
}
