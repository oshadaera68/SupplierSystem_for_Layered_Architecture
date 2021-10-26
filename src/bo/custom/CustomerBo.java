package bo.custom;

import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperBo {
    boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    Customer searchById(String cusId) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
}
