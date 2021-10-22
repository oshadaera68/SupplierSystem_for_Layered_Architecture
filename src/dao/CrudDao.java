package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T, ID> {
    boolean add(T o) throws SQLException, ClassNotFoundException;
    boolean delete(ID id) throws SQLException, ClassNotFoundException;
    boolean update(T o) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
}