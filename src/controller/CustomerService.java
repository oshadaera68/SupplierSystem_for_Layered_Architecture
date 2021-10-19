package controller;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerService {

    boolean addCustomer(Customer c) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    Customer getCustomer(String id) throws SQLException, ClassNotFoundException;
    ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException;

}
