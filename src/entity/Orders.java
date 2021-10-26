package entity;

import java.util.ArrayList;

public class Orders {
    private String orderID;
    private String orderDate;
    private String custID;
    private String orderTime;
    private double cost;
    private ArrayList<OrderDetail> details;

    public Orders() {
    }

    public Orders(String orderID, String orderDate, String custID, String orderTime, double cost, ArrayList<OrderDetail> details) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.custID = custID;
        this.orderTime = orderTime;
        this.cost = cost;
        this.details = details;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<OrderDetail> details) {
        this.details = details;
    }
}
