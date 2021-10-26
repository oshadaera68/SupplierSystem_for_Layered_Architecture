package dao.Custom;

import dao.CrudDao;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao extends CrudDao<Item, String> {
    boolean add(Item i) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(Item i) throws SQLException, ClassNotFoundException;

    ArrayList<Item> getAll() throws SQLException, ClassNotFoundException;

    Item searchById(String id) throws SQLException, ClassNotFoundException;

}
