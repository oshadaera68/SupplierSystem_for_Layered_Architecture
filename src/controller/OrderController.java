package controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import dao.OrderDaoImpl;
import db.DbConnection;
import model.ItemDetails;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    public String getOrderId() throws SQLException, ClassNotFoundException {
        return orderDao.OrderId();
    }

    public boolean saveOrderDetails(String id, ArrayList<ItemDetails> details) throws SQLException, ClassNotFoundException {
        return orderDao.saveOrderDetails(id, details);
    }

}