package dao.Custom.Impl;

import dao.Custom.CustomerDao;
import model.Customer;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean add(Customer o) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)", o.getId(), o.getTitle(), o.getName(), o.getAddress(), o.getCity(), o.getProvince(), o.getPostalCode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE id='" + id + "'", id);
    }

    @Override
    public boolean update(Customer o) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Customer SET CustTitle=?, CustName=?, CustAddress=?, City=?, Province=?, PostalCode=?  WHERE CustID=?", o.getTitle(), o.getName(), o.getAddress(), o.getCity(), o.getProvince(), o.getPostalCode(), o.getId());
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        while (rst.next()) {
            customers.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7)));
        }
        return customers;
    }

   /* @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        while (rst.next()) {
            customers.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7)));
        }
        return customers;
    }

    @Override
    public boolean addCustomer(Customer c) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)", c.getId(), c.getTitle(), c.getName(), c.getAddress(), c.getCity(), c.getProvince(), c.getPostalCode());
    }

    @Override
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Customer SET CustTitle=?, CustName=?, CustAddress=?, City=?, Province=?, PostalCode=?  WHERE CustID=?", c.getTitle(), c.getName(), c.getAddress(), c.getCity(), c.getProvince(), c.getPostalCode(), c.getId());
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE id='" + id + "'", id);
    }*/

}