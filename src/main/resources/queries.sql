drop table orders;
create table orders(
	ID int NOT NULL AUTO_INCREMENT,
	PRODUCT_ID varchar(255) NOT NULL,
	USER_ID varchar(255) NOT NULL,
	QUANTITY INT NOT NULL,
	TOTAL_PRICE FLOAT NOT NULL,
	PAYMENT_MODE varchar(255) NOT NULL,
	ADDRESS varchar(255) NOT NULL,
	CREATED_DATE TIMESTAMP,
	PRIMARY KEY (ID)
);

drop table subscription;
create table subscription(
	ID int NOT NULL AUTO_INCREMENT,
	ORDER_IDS BLOB,
	USER_ID varchar(255) NOT NULL,
	PRODUCT_ID varchar(255) NOT NULL,
	QUANTITY INT NOT NULL,
	TOTAL_PRICE FLOAT NOT NULL,
	PAYMENT_MODE varchar(255) NOT NULL,
	SUBSCRIPTION_START_DATE TIMESTAMP,
	FREQUENCY INT NOT NULL,
	NEXT_SUBSCRIPTION_DATE TIMESTAMP,
	SUBSCRIPTION_END_DATE TIMESTAMP,
	SUBSCRIPTION_STATUS varchar(255) NOT NULL,
	ADDRESS varchar(255) NOT NULL,
	IS_NOTIFICATION_PUSHED BOOLEAN,
	PRIMARY KEY (ID)
);