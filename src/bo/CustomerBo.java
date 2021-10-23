package bo;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo {
    public boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException;
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException;
    public Customer searchById(String cusId) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
}
