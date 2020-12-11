-- create sellers
insert into users values (1,'namco2011@gmail.com',true,false,'tam', false, 'nguyen', '12345', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 'VERIFIED','BUYER','VERIFIED', 'user', 'baon01','2020-12-08 21:50:41.644283',null);
insert into users values (2,'namco2011@gmail.com',true,false, 'test1', false, 'nguyen', '12345', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 'VERIFIED', 'BUYER','VERIFIED', 'user1', 'namn01','2020-12-08 21:50:41.644283',null);
insert into users values (3,'namco2011@gmail.com', true,false,'test2', false, 'nguyen', '12345', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 'VERIFIED', 'SELLER','NEED_TO_VERIFY', 'user2', 'tamn01','2020-12-08 21:50:41.644283',null);
-- create buyers
insert into users values (4,'bobby@miu.com',true,false, 'Bobby', false, 'Johnson', '12345', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 'VERIFIED', 'BUYER','VERIFIED', 'bobbyj','ganz01','2020-12-08 21:50:41.644283',null);
insert into users values (5,'john@miu.com',true,false, 'John', false, 'Doe', '45678', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 'VERIFIED', 'BUYER','VERIFIED', 'johnd', 'hale01','2020-12-08 21:50:41.644283',null);
insert into users values (6,'sam@miu.com',true,false, 'Sam', false, 'Cassel', '112233', '$2a$10$/6W.d4JZ2IiqMjo0vwxks.oH8pwurBlO376.UlEnNdhTXGRKjvVla', 'VERIFIED', 'BUYER','VERIFIED', 'samc', 'hale02','2020-12-08 21:50:41.644283',null);


--cat data
INSERT INTO CATEGORY VALUES (1, 'Phone', 'Phone');
INSERT INTO CATEGORY VALUES (2, 'Laptop', 'Laptop');
INSERT INTO CATEGORY VALUES (3, 'Table', 'Table');

--product data
INSERT into AUCTION (AUCTION_ID, BEGIN_DATE, END_DATE, AUCTION_STATUS, BEGIN_PRICE, WINNER_ID, SHIPPING_DATE, SHIPPING_STATUS)
                values (4, '2020-12-06 15:00:00', '2020-12-15 15:00:00', 1, 100 , null, null, null);
INSERT INTO PRODUCT VALUES(4,1, null , 'Apple iPhone 6s smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone6s', 100, 'SAVE_WITHOUT_RELEASE', 1,'2020-10-26',4 , 1);

INSERT into AUCTION (AUCTION_ID, BEGIN_DATE, END_DATE, AUCTION_STATUS, BEGIN_PRICE, WINNER_ID, SHIPPING_DATE, SHIPPING_STATUS)
                values (5, '2020-12-06 15:00:00', '2020-12-15 15:00:00', 1, 100 , null, null, null);
INSERT INTO PRODUCT VALUES(5,2, null , 'Dell Inspiron 14-inch Laptop with 3rd Generation Intel Core processors', 'Dell Inspiron',200, 'SAVE_WITHOUT_RELEASE', 1,'2020-10-27',5 ,2 );

INSERT into AUCTION (AUCTION_ID, BEGIN_DATE, END_DATE, AUCTION_STATUS, BEGIN_PRICE, WINNER_ID, SHIPPING_DATE, SHIPPING_STATUS)
                values (6, '2020-12-06 15:00:00', '2020-12-15 15:00:00', 1, 100 , null, null, null);
INSERT INTO PRODUCT VALUES(6,3, null , 'Google Nexus 7 is the lightest 7 inch tablet with a quad-core S4 Pro procrssor', 'Nexus 7',300, 'SAVE_WITHOUT_RELEASE', 2,'2020-10-28',6 ,3);

INSERT into AUCTION (AUCTION_ID, BEGIN_DATE, END_DATE, AUCTION_STATUS, BEGIN_PRICE, WINNER_ID, SHIPPING_DATE, SHIPPING_STATUS)
                values (7, '2020-12-06 15:00:00', '2020-12-15 15:00:00', 1, 100 , null, null, null);
INSERT INTO PRODUCT VALUES(7,1, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12',900, 'SAVE_WITHOUT_RELEASE', 1,'2020-11-02',7 , 1);

INSERT into AUCTION (AUCTION_ID, BEGIN_DATE, END_DATE, AUCTION_STATUS, BEGIN_PRICE, WINNER_ID, SHIPPING_DATE, SHIPPING_STATUS)
                values (8, '2020-12-06 15:00:00', '2020-12-15 15:00:00', 1, 100 , null, null, null);
INSERT INTO PRODUCT VALUES(8,2, null , 'Dell 2020 14-inch Laptop with 3rd Generation Intel Core processors', 'Dell 2020',1000, 'SAVE_WITHOUT_RELEASE', 1,'2020-11-10', 8 ,2);

INSERT into AUCTION (AUCTION_ID, BEGIN_DATE, END_DATE, AUCTION_STATUS, BEGIN_PRICE, WINNER_ID, SHIPPING_DATE, SHIPPING_STATUS)
                values (9, '2020-12-06 15:00:00', '2020-12-15 15:00:00', 1, 100 , null, null, null);
INSERT INTO PRODUCT VALUES(9,3, null , 'Google Nexus 2020 is the lightest 7 inch tablet with a quad-core S4 Pro procrssor', 'Nexus 2020',500, 'SAVE_WITHOUT_RELEASE', 2, '2020-11-25',9 ,3);


INSERT INTO PRODUCT VALUES(10,1, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12',900, 'SAVE_WITHOUT_RELEASE', 2, '2020-11-25',null , 1);
INSERT INTO PRODUCT VALUES(11,1, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12',900, 'SAVE_WITHOUT_RELEASE', 2, '2020-11-25',null , 1);
INSERT INTO PRODUCT VALUES(12,1, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12',900, 'SAVE_WITHOUT_RELEASE', 2, '2020-11-25',null , 1);
INSERT INTO PRODUCT VALUES(13,1, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12',900, 'SAVE_WITHOUT_RELEASE', 2, '2020-11-25',null , 1);
INSERT INTO PRODUCT VALUES(14,1, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12',900, 'SAVE_WITHOUT_RELEASE', 2, '2020-11-25',null , 1);
INSERT INTO PRODUCT VALUES(15,1, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12',900, 'SAVE_WITHOUT_RELEASE', 2, '2020-11-25',null , 1);
INSERT INTO PRODUCT VALUES(16,1, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12',900, 'SAVE_WITHOUT_RELEASE', 2, '2020-11-25',null , 1);
INSERT INTO PRODUCT VALUES(17,1, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12',900, 'SAVE_WITHOUT_RELEASE', 2, '2020-11-25',null , 1);
INSERT INTO PRODUCT VALUES(18,1, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12',900, 'SAVE_WITHOUT_RELEASE', 2, '2020-11-25',null , 1);
INSERT INTO PRODUCT VALUES(19,1, null , 'Apple iPhone 12 smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone12',900, 'SAVE_WITHOUT_RELEASE', 2, '2020-11-25',null , 1);

-- insert 3 products, each product should have 1 auction
INSERT into AUCTION (AUCTION_ID, BEGIN_DATE, END_DATE, AUCTION_STATUS, BEGIN_PRICE, WINNER_ID, SHIPPING_DATE, SHIPPING_STATUS)
                values (247, '2020-12-06 15:00:00', '2020-12-15 15:00:00', 1, 100 , null, null, null);

INSERT INTO PRODUCT (PRODUCT_ID,CATEGORY_ID, PAYMENT_DUE_DATE, PRODUCT_DESCRIPTION, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_STATE, PRODUCT_STATUS, UPLOAD_DATE,AUCTION_ID, SELLER_USER_ID)
            VALUES(1,1, null , 'Apple iPhone 6s smartphone with 4.00-inch 640X1136 diplay and 8-megapixel rear camera', 'Iphone7s',250, 'SAVE_AND_RELEASE', 1,'2020-11-16', 247, 1);

INSERT into AUCTION (AUCTION_ID, BEGIN_DATE, END_DATE, AUCTION_STATUS, BEGIN_PRICE, WINNER_ID, SHIPPING_DATE, SHIPPING_STATUS)
                values (248, '2020-12-06 15:00:00', '2020-12-31 15:00:00', 1, 150 , null, null, null);

INSERT INTO PRODUCT (PRODUCT_ID,CATEGORY_ID, PAYMENT_DUE_DATE, PRODUCT_DESCRIPTION, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_STATE, PRODUCT_STATUS, UPLOAD_DATE,AUCTION_ID, SELLER_USER_ID)
            VALUES(2,2, null , 'Dell Inspiron 14-inch Laptop with 3rd Generation Intel Core processors', 'Dell Inspiron',110, 'SAVE_AND_RELEASE', 1,'2020-12-28', 248, 2);

INSERT into AUCTION (AUCTION_ID, BEGIN_DATE, END_DATE, AUCTION_STATUS, BEGIN_PRICE, WINNER_ID, SHIPPING_DATE, SHIPPING_STATUS)
                values (249, '2020-12-06 15:00:00', '2020-12-28 17:00:00', 1, 50 , null, null, null);

INSERT INTO PRODUCT (PRODUCT_ID,CATEGORY_ID, PAYMENT_DUE_DATE, PRODUCT_DESCRIPTION, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_STATE, PRODUCT_STATUS, UPLOAD_DATE,AUCTION_ID, SELLER_USER_ID)
            VALUES(3,3, null , 'Google Nexus 7 is the lightest 7 inch tablet with a quad-core S4 Pro procrssor', 'Nexus 7',90, 'SAVE_AND_RELEASE', 1,'2020-09-26', 249, 3);

--image data
INSERT INTO IMAGE VALUES (1, 'iphone6s.jpg');
INSERT INTO IMAGE VALUES (2, 'dell_1.jpg');
INSERT INTO IMAGE VALUES (3, 'nexus.jpg');
INSERT INTO IMAGE VALUES (4,  'iphone7s_2.jpg');
INSERT INTO IMAGE VALUES (5,  'del2.jpg');
INSERT INTO IMAGE VALUES (6,  'nexus2.jpg');
INSERT INTO IMAGE VALUES (7,  'iphone12.jpeg');
INSERT INTO IMAGE VALUES (8,  'dell2020.jpg');
INSERT INTO IMAGE VALUES (9,  'nexus.jpg');
INSERT INTO IMAGE VALUES (10,  'iphone12_2.png');
INSERT INTO IMAGE VALUES (11,  'del2.jpg');
INSERT INTO IMAGE VALUES (12,  'nexus2.jpg');
INSERT INTO IMAGE VALUES (13,  'iphone7s_3.jpg');

INSERT INTO PRODUCT_DB_IMAGES VALUES(8, 2);
INSERT INTO PRODUCT_DB_IMAGES VALUES(8, 5);
INSERT INTO PRODUCT_DB_IMAGES VALUES(8, 8);
INSERT INTO PRODUCT_DB_IMAGES VALUES(8, 11);
INSERT INTO PRODUCT_DB_IMAGES VALUES(7, 7);
INSERT INTO PRODUCT_DB_IMAGES VALUES(7, 1);
INSERT INTO PRODUCT_DB_IMAGES VALUES(7, 10);
INSERT INTO PRODUCT_DB_IMAGES VALUES(7, 13);
INSERT INTO PRODUCT_DB_IMAGES VALUES(3, 12);



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

INSERT INTO DMV VALUES (1, 'user@miu', 'test1', 'nguyen', '12345');











