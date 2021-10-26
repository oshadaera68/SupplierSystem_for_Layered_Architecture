package bo.custom;

import entity.OrderDetail;
import entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBo extends SuperBo {
    ArrayList<OrderDetail> saveOrderDetails(String id, OrderDetail details) throws SQLException, ClassNotFoundException;

    String orderId() throws SQLException, ClassNotFoundException;

    Boolean purchaseOrder(Orders o, ArrayList<OrderDetail> orderDetails) throws SQLException, ClassNotFoundException;
}

