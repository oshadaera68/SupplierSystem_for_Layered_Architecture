package bo.custom;

import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo extends SuperBo{
    boolean addItem(Item item) throws SQLException, ClassNotFoundException;

    Item searchById(String itemId) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException;

    ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException;

    boolean updateItem(Item item) throws SQLException, ClassNotFoundException;
}
