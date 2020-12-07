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

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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

    public void setBeginPrice(Double beginPrice) {
        this.beginPrice = beginPrice;
    }

    public Long getWinnerBidId() {
        return winnerBidId;
    }

    public void setWinnerBidId(Long winnerBidId) {
        this.winnerBidId = winnerBidId;
    }

    public List<Bid> getBidId() {
        return bidId;
    }

    public void setBidId(List<Bid> bidId) {
        this.bidId = bidId;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
