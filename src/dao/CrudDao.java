package dao;

import java.util.ArrayList;

public interface CrudDao {
    boolean add(Object o);
    boolean delete(Object o);
    boolean update(Object o);
    ArrayList<Object> getAll();
}
