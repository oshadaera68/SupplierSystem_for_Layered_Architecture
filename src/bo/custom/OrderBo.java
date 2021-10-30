package bo.custom;

import entity.OrderDetail;
import entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBo extends SuperBo {
    String orderId() throws SQLException, ClassNotFoundException;

    boolean placeOrder(Orders orders, ArrayList<OrderDetail> orderDetails) throws SQLException, ClassNotFoundException;

    boolean saveOrderDetails(String id, ArrayList<OrderDetail> details) throws SQLException, ClassNotFoundException;
}
