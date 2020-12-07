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

SELECT * FROM AUCTION

INSERT into AUCTION (auction_id, auction_status, begin_date, begin_price, end_date, shipping_date, shipping_status, winner_bid_id) values (1, 1, "2020-12-06 15:00:00", "2020-12-07 15:00:00", );

INSERT INTO ROLE(role_id, role) VALUES (1, 'ROLE_USER');
INSERT INTO ROLE(role_id, role) VALUES (2, 'ROLE_ADMIN');

INSERT into USER (user_id, username, password, active) values (1, 'user1', '$2a$12$jUNLxJ5vdppm0Tx23.TEnu9LwOZY07l9oVakw7HhHKkDHhwx0tYTa', 1);
INSERT into USER (user_id, username, password, active) values (2, 'user2', '$2a$12$jUNLxJ5vdppm0Tx23.TEnu9LwOZY07l9oVakw7HhHKkDHhwx0tYTa', 1);
INSERT into USER (user_id, username, password, active) values (3, 'user3', '$2a$12$jUNLxJ5vdppm0Tx23.TEnu9LwOZY07l9oVakw7HhHKkDHhwx0tYTa', 1);
INSERT into USER (user_id, username, password, active) values (4, 'user4', '$2a$12$jUNLxJ5vdppm0Tx23.TEnu9LwOZY07l9oVakw7HhHKkDHhwx0tYTa', 1);
INSERT into USER (user_id, username, password, active) values (5, 'admin', '$2a$12$jUNLxJ5vdppm0Tx23.TEnu9LwOZY07l9oVakw7HhHKkDHhwx0tYTa', 1);

insert into user_role(user_id, role_id) values (1, 1);
insert into user_role(user_id, role_id) values (1, 2);
insert into user_role(user_id, role_id) values (2, 1);
insert into user_role(user_id, role_id) values (3, 1);
insert into user_role(user_id, role_id) values (4, 1);
insert into user_role(user_id, role_id) values (5, 2);
