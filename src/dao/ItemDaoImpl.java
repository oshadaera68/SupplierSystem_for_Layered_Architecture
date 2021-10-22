package dao;

import model.Item;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements CrudDao<Item, String> {

    @Override
    public boolean add(Item o) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?)", o.getItemCode(), o.getDescription(), o.getPackSize(), o.getUnitPrice(), o.getQtyOnHand());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE ItemCode='" + id + "'", id);
    }

    @Override
    public boolean update(Item o) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        while (rst.next()) {
            allItems.add(new Item(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getInt(5)));
        }
        return allItems;
    }

   /* @Override
    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        while (rst.next()) {
            allItems.add(new Item(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getInt(5)));
        }
        return allItems;
    }

    @Override
    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Customer SET CustTitle=?, CustName=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=?", i.getDescription(), i.getPackSize(), i.getUnitPrice(), i.getQtyOnHand(), i.getItemCode());
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE ItemCode='" + code + "'", code);
    }

    @Override
    public boolean addItem(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?)", i.getItemCode(), i.getDescription(), i.getPackSize(), i.getUnitPrice(), i.getQtyOnHand());
    }*/

}