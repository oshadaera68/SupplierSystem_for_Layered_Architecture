package DAO;

import db.DbConnection;
import javafx.scene.control.Alert;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl {
    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems = new ArrayList();
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item");
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            allItems.add(new Item(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getInt(5)));
        }
        return allItems;
    }

    public boolean addItem(Item it) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Item VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, it.getItemCode());
        stm.setObject(2, it.getDescription());
        stm.setObject(3, it.getPackSize());
        stm.setObject(4, it.getUnitPrice());
        stm.setObject(5, it.getQtyOnHand());

        return stm.executeUpdate() > 0;
    }

    public boolean updateItem(Item it) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET Description=?, PackSize=?, UnitPrice=?,QtyOnHand=? WHERE ItemCode=?");
        stm.setObject(1, it.getDescription());
        stm.setObject(2, it.getPackSize());
        stm.setObject(3, it.getUnitPrice());
        stm.setObject(4, it.getQtyOnHand());
        stm.setObject(5, it.getItemCode());
        return stm.executeUpdate() > 0;
    }

    public boolean existsCustomer(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE ItemCode=?");
        stm.setObject(1, id);
        return stm.executeQuery().next();
    }
}
