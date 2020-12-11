package edu.miu.cs.neptune.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Auction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    private AuctionStatus auctionStatus;
    private Double beginPrice;
    private Long winnerId;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Bid> bids = new ArrayList<>();
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

    public void setProduct(Product product) {
        this.product = product;
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

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setBeginPrice(Double beginPrice) {
        this.beginPrice = beginPrice;
    }
}
