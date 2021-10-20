package dao;

import db.DbConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl {

    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList();
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            customers.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7)));
        }
        return customers;
    }

    public boolean addCustomer(Customer c) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)");
        stm.setObject(1, c.getId());
        stm.setObject(2, c.getTitle());
        stm.setObject(3, c.getName());
        stm.setObject(4, c.getAddress());
        stm.setObject(5, c.getCity());
        stm.setObject(6, c.getProvince());
        stm.setObject(7, c.getPostalCode());
        return stm.executeUpdate() > 0;
    }

    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET CustTitle=?, =?, CustName=?, CustAddress=?, City=?, Province=?, PostalCode=?  WHERE CustID=?");
        stm.setObject(1, c.getTitle());
        stm.setObject(2, c.getName());
        stm.setObject(3, c.getAddress());
        stm.setObject(4, c.getCity());
        stm.setObject(5, c.getProvince());
        stm.setObject(6, c.getPostalCode());
        stm.setObject(7, c.getId());
        return stm.executeUpdate() > 0;
    }

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE id='" + id + "'").executeUpdate() > 0;
    }

    public List<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }


}
