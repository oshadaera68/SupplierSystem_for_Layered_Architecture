package controller;

import dao.Custom.Impl.OrderDaoImpl;
import model.ItemDetails;

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