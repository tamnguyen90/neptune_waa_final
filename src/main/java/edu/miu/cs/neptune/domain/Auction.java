package edu.miu.cs.neptune.domain;

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
    private Long winnerId;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<Bid> bids;
    private LocalDateTime shippingDate;
    private ShippingStatus shippingStatus;
    @OneToOne(mappedBy = "auction", cascade = CascadeType.ALL)
    private Product product;

    public Auction() {}
    public Auction(LocalDateTime beginDate, LocalDateTime endDate, Double beginPrice) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.beginPrice = beginPrice;
    }

    public Product getProduct() {
        return product;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public AuctionStatus getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(AuctionStatus auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public Double getBeginPrice() {
        return beginPrice;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerBidId) {
        this.winnerId = winnerBidId;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public void addBid(Bid bid) {
        this.bids.add(bid);
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
}
