package dao;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao {
    ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException;
    boolean addCustomer(Customer c) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
}