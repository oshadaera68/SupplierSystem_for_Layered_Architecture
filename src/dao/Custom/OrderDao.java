package dao.Custom;

import dao.SuperDao;
import entity.OrderDetail;
import entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDao extends SuperDao {
    Boolean purchaseOrder(Orders o, ArrayList<OrderDetail> orderDetails) throws SQLException, ClassNotFoundException;

    boolean saveOrderDetails(String id, ArrayList<OrderDetail> details) throws SQLException, ClassNotFoundException;

    String orderId() throws SQLException, ClassNotFoundException;
}
