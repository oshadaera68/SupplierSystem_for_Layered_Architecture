package dao.Custom;

import dao.SuperDao;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao extends SuperDao {
    boolean add(Customer c) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(Customer c) throws SQLException, ClassNotFoundException;

    ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException;

    Customer searchById(String id) throws SQLException, ClassNotFoundException;
}
