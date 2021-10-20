package dao;

import db.DbConnection;
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

    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET CustTitle=?, CustName=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=? ");
        stm.setObject(1, i.getDescription());
        stm.setObject(2, i.getPackSize());
        stm.setObject(3, i.getUnitPrice());
        stm.setObject(4, i.getQtyOnHand());
        stm.setObject(5, i.getItemCode());
        return stm.executeUpdate() > 0;
    }

    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE ItemCode='" + code + "'").executeUpdate() > 0;
    }

    public boolean addItem(Item i) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Item VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, i.getItemCode());
        stm.setObject(2, i.getDescription());
        stm.setObject(3, i.getPackSize());
        stm.setObject(4, i.getUnitPrice());
        stm.setObject(5, i.getQtyOnHand());
        return stm.executeUpdate() > 0;
    }

   /* public boolean getItem(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE id=?");
        stm.setString(1, id);
        return stm.executeQuery().next();


    }*/
}