package dto;

import entity.OrderDetail;

public class OrderDetailsDto extends OrderDetail {
    private String OrderId;
    private String ItemCode;
    private int OrderQty;
    private double Discount;

    public OrderDetailsDto() {
    }

    public OrderDetailsDto(String orderId, String itemCode, int orderQty, double discount) {
        OrderId = orderId;
        ItemCode = itemCode;
        OrderQty = orderQty;
        Discount = discount;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public int getOrderQty() {
        return OrderQty;
    }

    public void setOrderQty(int orderQty) {
        OrderQty = orderQty;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }
}
