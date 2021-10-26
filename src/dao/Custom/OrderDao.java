package dao.Custom;

import dao.SuperDao;
import entity.OrderDetail;
import entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDao extends SuperDao {
    String orderId() throws SQLException, ClassNotFoundException;

    boolean PlaceOrder(Orders o, ArrayList<OrderDetail> orderDetails)throws SQLException, ClassNotFoundException;

    ArrayList<OrderDetail> saveOrderDetails(String id, ArrayList<OrderDetail> details) throws SQLException, ClassNotFoundException;
}