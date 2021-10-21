package dao;

import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao {
    ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException;

    boolean updateItem(Item i) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

    boolean addItem(Item i) throws SQLException, ClassNotFoundException;


}
