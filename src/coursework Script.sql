DROP DATABASE IF EXISTS SupplierSystem;
CREATE DATABASE IF NOT EXISTS SupplierSystem;
USE SupplierSystem;
SHOW DATABASES;
#------------------------------------------
DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    CustID VARCHAR(20),
    CustTitle VARCHAR(5),
    CustName VARCHAR(30),
    CustAddress VARCHAR(30),
    City VARCHAR(20),
    Province VARCHAR(20),
    PostalCode VARCHAR(9),
    CONSTRAINT PRIMARY KEY (CustId)
    );
SHOW TABLES;
DESC Customer;
#----------------------------------------------
DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item(
    ItemCode VARCHAR(20),
    Description VARCHAR(50),
    PackSize VARCHAR(20),
    UnitPrice DECIMAL(6,2),
    QtyOnHand INT(5),
    CONSTRAINT PRIMARY KEY (ItemCode)
    );
SHOW TABLES;
DESC Item;
#----------------------------------------------
DROP TABLE IF EXISTS Orders;
CREATE TABLE IF NOT EXISTS Orders(
    OrderID VARCHAR(20),
    OrderDate VARCHAR(20),
    CustID VARCHAR(20),
    OrderTime VARCHAR(20),
    Cost DOUBLE,
    CONSTRAINT PRIMARY KEY (OrderID),
    CONSTRAINT FOREIGN KEY (CustID) REFERENCES Customer (CustID) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESC Orders;
#-----------------------------------------------
DROP TABLE IF EXISTS OrderDetail;
CREATE TABLE IF NOT EXISTS OrderDetail(
    OrderID VARCHAR(20),
    ItemCode VARCHAR(20),
    OrderQty INT(11),
    Discount DECIMAL(6,2),
    CONSTRAINT PRIMARY KEY (OrderID,ItemCode),
    CONSTRAINT FOREIGN KEY (OrderID) REFERENCES Orders (OrderID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (ItemCode) REFERENCES Item (ItemCode) ON DELETE CASCADE ON UPDATE CASCADE
    );
