package model;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String OrderDate;
    private String OrderTime;
    private double cost;
    private String cusId;
    private ArrayList<ItemDetails> details;

    public Order() {
    }

    public Order(String orderId, String orderDate, String orderTime, double cost, String cusId, ArrayList<ItemDetails> details) {
        this.orderId = orderId;
        OrderDate = orderDate;
        OrderTime = orderTime;
        this.cost = cost;
        this.cusId = cusId;
        this.details = details;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public ArrayList<ItemDetails> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<ItemDetails> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", OrderDate='" + OrderDate + '\'' +
                ", OrderTime='" + OrderTime + '\'' +
                ", cost=" + cost +
                ", cusId='" + cusId + '\'' +
                ", details=" + details +
                '}';
    }
}
