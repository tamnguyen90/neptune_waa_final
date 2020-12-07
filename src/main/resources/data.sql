-- create sellers
insert into users values (1,'user@miu', 'tam', false, 'nguyen', '12345', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 1, 'user', null);
insert into users values (2,'user@miu', 'test1', false, 'nguyen', '12345', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 1, 'user', null);
insert into users values (3,'user@miu', 'test2', false, 'nguyen', '12345', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 1, 'user', null);

-- insert 3 products, each product should have 1 auction
INSERT into AUCTION (auction_id, begin_date, end_date, auction_status, begin_price, winner_bid_id, shipping_date, shipping_status)
                values (247, '2020-12-06 15:00:00', '2020-12-15 15:00:00', 1, 100 , null, null, null);

INSERT INTO PRODUCT (PRODUCT_ID, PAYMENT_DUE_DATE, PRODUCT_DESCRIPTION, PRODUCT_NAME, PRODUCT_STATUS, AUCTION_ID, SELLER_USER_ID)
            VALUES(1, null , 'Apple iPhone 6s smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone6s', 1, 247, 1);

INSERT into AUCTION (auction_id, begin_date, end_date, auction_status, begin_price, winner_bid_id, shipping_date, shipping_status)
                values (248, '2020-12-06 15:00:00', '2020-12-31 15:00:00', 1, 150 , null, null, null);

INSERT INTO PRODUCT (PRODUCT_ID, PAYMENT_DUE_DATE, PRODUCT_DESCRIPTION, PRODUCT_NAME, PRODUCT_STATUS, AUCTION_ID, SELLER_USER_ID)
            VALUES(2, null , 'Dell Inspiron 14-inch Laptop with 3rd Generation Intel Core processors', 'Dell Inspiron', 1, 248, 2);

INSERT into AUCTION (auction_id, begin_date, end_date, auction_status, begin_price, winner_bid_id, shipping_date, shipping_status)
                values (249, '2020-12-06 15:00:00', '2020-12-28 17:00:00', 1, 50 , null, null, null);

INSERT INTO PRODUCT (PRODUCT_ID, PAYMENT_DUE_DATE, PRODUCT_DESCRIPTION, PRODUCT_NAME, PRODUCT_STATUS, AUCTION_ID, SELLER_USER_ID)
            VALUES(3, null , 'Google Nexus 7 is the lightest 7 inch tablet with a quad-core S4 Pro procrssor', 'Nexus 7', 1, 249, 3);

INSERT INTO CATEGORY VALUES (1, 'Phone', 'Phone');
INSERT INTO CATEGORY VALUES (2, 'Laptop', 'Laptop');
INSERT INTO CATEGORY VALUES (3, 'Table', 'Table');

INSERT INTO PRODUCT VALUES(1, null , 'Apple iPhone 6s smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone6s', 1, 1,1 );

INSERT INTO PRODUCT VALUES(2, null , 'Dell Inspiron 14-inch Laptop with 3rd Generation Intel Core processors', 'Dell Inspiron', 1,2,2 );

INSERT INTO PRODUCT VALUES(3, null , 'Google Nexus 7 is the lightest 7 inch tablet with a quad-core S4 Pro procrssor', 'Nexus 7', 2,3,3);

INSERT INTO PRODUCT VALUES(4, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12', 1, 1,1 );

INSERT INTO PRODUCT VALUES(5, null , 'Dell 2020 14-inch Laptop with 3rd Generation Intel Core processors', 'Dell 2020', 1,2,2 );

INSERT INTO PRODUCT VALUES(6, null , 'Google Nexus 2020 is the lightest 7 inch tablet with a quad-core S4 Pro procrssor', 'Nexus 2020', 2,3,3);




INSERT INTO IMAGE VALUES (1, NULL , 'IPhone6s');
INSERT INTO IMAGE VALUES (2, NULL , 'Dell');
INSERT INTO IMAGE VALUES (3, NULL , 'Nexus');

-- create buyers
insert into users (USER_ID, EMAIL, FIRST_NAME, IS_RESET_PASSWORD, LAST_NAME, LICENSE_NUMBER, PASSWORD, PROFILE_VERIFICATION_TYPE, USERNAME, ADDRESS_ADDRESS_ID)
                values (4,'bobby@miu.com', 'Bobby', false, 'Johnson', '12345', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 1, 'bobbyj', null);
insert into users (USER_ID, EMAIL, FIRST_NAME, IS_RESET_PASSWORD, LAST_NAME, LICENSE_NUMBER, PASSWORD, PROFILE_VERIFICATION_TYPE, USERNAME, ADDRESS_ADDRESS_ID)
                values (5,'john@miu.com', 'John', false, 'Doe', '45678', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 1, 'johnd', null);
insert into users (USER_ID, EMAIL, FIRST_NAME, IS_RESET_PASSWORD, LAST_NAME, LICENSE_NUMBER, PASSWORD, PROFILE_VERIFICATION_TYPE, USERNAME, ADDRESS_ADDRESS_ID)
                values (6,'sam@miu.com', 'Sam', false, 'Cassel', '112233', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 1, 'samc', null);

-- create bid for each user on product id: 247
INSERT into BID (ID, AMOUNT, BIDDING_TIME) values (101, 120, '2020-12-06 17:00:00');
INSERT into BID (ID, AMOUNT, BIDDING_TIME) values (102, 130, '2020-12-06 17:30:00');
INSERT into BID (ID, AMOUNT, BIDDING_TIME) values (103, 140, '2020-12-06 18:00:00');
INSERT into BID (ID, AMOUNT, BIDDING_TIME) values (104, 150, '2020-12-06 19:00:00');

INSERT into USERS_BIDS (USER_USER_ID, BIDS_ID) values (4, 101);
INSERT into USERS_BIDS (USER_USER_ID, BIDS_ID) values (5, 102);
INSERT into USERS_BIDS (USER_USER_ID, BIDS_ID) values (4, 103);
INSERT into USERS_BIDS (USER_USER_ID, BIDS_ID) values (6, 104);

INSERT into AUCTION_BID_ID (AUCTION_AUCTION_ID, BID_ID_ID) values (247, 101);
INSERT into AUCTION_BID_ID (AUCTION_AUCTION_ID, BID_ID_ID) values (247, 102);
INSERT into AUCTION_BID_ID (AUCTION_AUCTION_ID, BID_ID_ID) values (247, 103);
INSERT into AUCTION_BID_ID (AUCTION_AUCTION_ID, BID_ID_ID) values (247, 104);












