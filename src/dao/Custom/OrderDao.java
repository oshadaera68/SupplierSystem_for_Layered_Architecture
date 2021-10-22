package dao.Custom;

import model.ItemDetails;
import model.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDao {
    String OrderId() throws SQLException, ClassNotFoundException;

    boolean placeOrder(Order o)throws SQLException, ClassNotFoundException;

    boolean saveOrderDetails(String id, ArrayList<ItemDetails> details) throws SQLException, ClassNotFoundException;
}
