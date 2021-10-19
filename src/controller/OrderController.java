package controller;

import db.DbConnection;
import model.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {
    public boolean placeOrder(Order o){
        try {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Orders VALUES(?,?,?,?,?)");
            stm.setObject(1,o.getOrderId());
            stm.setObject(2,o.getOrderDate());
            stm.setObject(3,o.getCusId());
            stm.setObject(4,o.getOrderTime());
            stm.setObject(5,o.getCost());

            return stm.executeUpdate()>0;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
