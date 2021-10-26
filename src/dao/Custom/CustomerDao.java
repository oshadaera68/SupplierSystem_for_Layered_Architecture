package dao.Custom;

import dao.CrudDao;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao extends CrudDao<Customer, String> {
    boolean add(Customer o) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(Customer o) throws SQLException, ClassNotFoundException;

    ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException;

    Customer searchById(String s) throws SQLException, ClassNotFoundException;
}
