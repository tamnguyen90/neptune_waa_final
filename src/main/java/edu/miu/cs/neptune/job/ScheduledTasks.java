package edu.miu.cs.neptune.job;

import edu.miu.cs.neptune.domain.Auction;
import edu.miu.cs.neptune.domain.AuctionStatus;
import edu.miu.cs.neptune.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    AuctionService auctionService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "MM/dd/yyyy HH:mm:ss");

//    @Scheduled(fixedRate = 10000)
//    public void performTask() {
//
//        System.out.println("Regular task performed at "
//                + dateFormat.format(new Date()));
//
//    }

    // check if user hasn't paid the product within allowed time,
    // if yes, remove the pay button, don't refund the deposit
    @Scheduled(initialDelay = 5000, fixedRate = 60000)
    public void performDelayedTask() {
        List<Auction> list = auctionService.getAllEndedAuction();
        for (Auction auction : list) {
            if (auction.getProduct().getPaymentDueDate().compareTo(LocalDateTime.now())<0) {
                // buying time is expired
                System.out.println("Auction is over but haven't paid within given time");
                System.out.println("auctionProduct:"+auction.getProduct().getProductName());
                auction.setAuctionStatus(AuctionStatus.NOT_PAID);
                auctionService.save(auction);
            }
        }

        System.out.println("Regular interval task performed at "
                + dateFormat.format(new Date()));

    }

}
