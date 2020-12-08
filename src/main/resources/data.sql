-- create sellers
insert into users values (1,'user@miu', 'tam', false, 'nguyen', '12345', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 1,'BUYER', 'user', null);
insert into users values (2,'user@miu', 'test1', false, 'nguyen', '12345', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 1, 'BUYER', 'user1', null);
insert into users values (3,'user@miu', 'test2', false, 'nguyen', '12345', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 1, 'SELLER', 'user2', null);



--cat data
INSERT INTO CATEGORY VALUES (1, 'Phone', 'Phone');
INSERT INTO CATEGORY VALUES (2, 'Laptop', 'Laptop');
INSERT INTO CATEGORY VALUES (3, 'Table', 'Table');

--product data
INSERT INTO PRODUCT VALUES(4, null , 'Apple iPhone 6s smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone6s', 100, 1,'2020-10-26',null , 1,1 );

INSERT INTO PRODUCT VALUES(5, null , 'Dell Inspiron 14-inch Laptop with 3rd Generation Intel Core processors', 'Dell Inspiron',200, 1,'2020-10-27',null ,2,2 );

INSERT INTO PRODUCT VALUES(6, null , 'Google Nexus 7 is the lightest 7 inch tablet with a quad-core S4 Pro procrssor', 'Nexus 7',300, 2,'2020-10-28',null ,3,3);

INSERT INTO PRODUCT VALUES(7, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12',900, 1,'2020-11-02',null , 1,1 );

INSERT INTO PRODUCT VALUES(8, null , 'Dell 2020 14-inch Laptop with 3rd Generation Intel Core processors', 'Dell 2020',1000, 1,'2020-11-10', null ,2,2 );

INSERT INTO PRODUCT VALUES(9, null , 'Google Nexus 2020 is the lightest 7 inch tablet with a quad-core S4 Pro procrssor', 'Nexus 2020',500, 2, '2020-11-25',null ,3,3);

-- insert 3 products, each product should have 1 auction
INSERT into AUCTION (AUCTION_ID, BEGIN_DATE, END_DATE, AUCTION_STATUS, BEGIN_PRICE, WINNER_ID, SHIPPING_DATE, SHIPPING_STATUS)
                values (247, '2020-12-06 15:00:00', '2020-12-15 15:00:00', 1, 100 , null, null, null);

INSERT INTO PRODUCT (PRODUCT_ID, PAYMENT_DUE_DATE, PRODUCT_DESCRIPTION, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_STATUS, UPLOAD_DATE, AUCTION_ID, SELLER_USER_ID, PRODUCTS_CATEGORY_ID)
            VALUES(1, null , 'Apple iPhone 6s smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone7s',250, 1,'2020-11-16', 247, 1, 1);

INSERT into AUCTION (AUCTION_ID, BEGIN_DATE, END_DATE, AUCTION_STATUS, BEGIN_PRICE, WINNER_ID, SHIPPING_DATE, SHIPPING_STATUS)
                values (248, '2020-12-06 15:00:00', '2020-12-31 15:00:00', 1, 150 , null, null, null);

INSERT INTO PRODUCT (PRODUCT_ID, PAYMENT_DUE_DATE, PRODUCT_DESCRIPTION, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_STATUS, UPLOAD_DATE, AUCTION_ID, SELLER_USER_ID, PRODUCTS_CATEGORY_ID)
            VALUES(2, null , 'Dell Inspiron 14-inch Laptop with 3rd Generation Intel Core processors', 'Dell Inspiron',110, 1,'2020-12-28', 248, 2, 2);

INSERT into AUCTION (AUCTION_ID, BEGIN_DATE, END_DATE, AUCTION_STATUS, BEGIN_PRICE, WINNER_ID, SHIPPING_DATE, SHIPPING_STATUS)
                values (249, '2020-12-06 15:00:00', '2020-12-28 17:00:00', 1, 50 , null, null, null);

INSERT INTO PRODUCT (PRODUCT_ID, PAYMENT_DUE_DATE, PRODUCT_DESCRIPTION, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_STATUS, UPLOAD_DATE, AUCTION_ID, SELLER_USER_ID, PRODUCTS_CATEGORY_ID)
            VALUES(3, null , 'Google Nexus 7 is the lightest 7 inch tablet with a quad-core S4 Pro procrssor', 'Nexus 7',90, 1,'2020-09-26', 249, 3, 3);

INSERT INTO IMAGE VALUES (1, NULL , 'IPhone6s');
INSERT INTO IMAGE VALUES (2, NULL , 'Dell');
INSERT INTO IMAGE VALUES (3, NULL , 'Nexus');

-- create buyers
insert into users (USER_ID, EMAIL, FIRST_NAME, IS_RESET_PASSWORD, LAST_NAME, LICENSE_NUMBER, PASSWORD, PROFILE_VERIFICATION_TYPE, ROLE, USERNAME, ADDRESS_ADDRESS_ID)
                values (4,'bobby@miu.com', 'Bobby', false, 'Johnson', '12345', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 1, 'BUYER', 'bobbyj', null);
insert into users (USER_ID, EMAIL, FIRST_NAME, IS_RESET_PASSWORD, LAST_NAME, LICENSE_NUMBER, PASSWORD, PROFILE_VERIFICATION_TYPE, ROLE, USERNAME, ADDRESS_ADDRESS_ID)
                values (5,'john@miu.com', 'John', false, 'Doe', '45678', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 1, 'BUYER', 'johnd', null);
insert into users (USER_ID, EMAIL, FIRST_NAME, IS_RESET_PASSWORD, LAST_NAME, LICENSE_NUMBER, PASSWORD, PROFILE_VERIFICATION_TYPE, ROLE, USERNAME, ADDRESS_ADDRESS_ID)
                values (6,'sam@miu.com', 'Sam', false, 'Cassel', '112233', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 1, 'BUYER', 'samc', null);

-- create bid for each user on product id: 247
INSERT into BID (ID, AMOUNT, BIDDING_TIME, AUCTION_ID, USER_ID) values (101, 120, '2020-12-06 17:00:00', 247, 4);
INSERT into BID (ID, AMOUNT, BIDDING_TIME, AUCTION_ID, USER_ID) values (102, 130, '2020-12-06 17:30:00', 247, 5);
INSERT into BID (ID, AMOUNT, BIDDING_TIME, AUCTION_ID, USER_ID) values (103, 140, '2020-12-06 18:00:00', 247, 4);
INSERT into BID (ID, AMOUNT, BIDDING_TIME, AUCTION_ID, USER_ID) values (104, 150, '2020-12-06 19:00:00', 247, 6);
--
INSERT into USERS_BIDS (USER_USER_ID, BIDS_ID) values (4, 101);
INSERT into USERS_BIDS (USER_USER_ID, BIDS_ID) values (5, 102);
INSERT into USERS_BIDS (USER_USER_ID, BIDS_ID) values (4, 103);
INSERT into USERS_BIDS (USER_USER_ID, BIDS_ID) values (6, 104);
