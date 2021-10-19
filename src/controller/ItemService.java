package controller;

import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemService {
    boolean addItem(Item i) throws SQLException, ClassNotFoundException;

    boolean updateItem(Item i) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    Item getItem(String id) throws SQLException, ClassNotFoundException;

    ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException;
}
