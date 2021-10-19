package DAO;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Customer;
import model.Item;
import views.Tm.CustomerTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl {

    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            allCustomers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return allCustomers;
    }

    public boolean addCustomer(Customer cus) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, cus.getId());
        stm.setObject(2, cus.getTitle());
        stm.setObject(3, cus.getName());
        stm.setObject(4, cus.getAddress());
        stm.setObject(5, cus.getCity());
        stm.setObject(6, cus.getProvince());
        stm.setObject(7, cus.getPostalCode());
        return stm.executeUpdate() > 0;
    }

    public boolean updateCustomer(Customer cus) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET CustTitle=?, CustName=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=? ");
        stm.setObject(1, cus.getTitle());
        stm.setObject(2, cus.getName());
        stm.setObject(3, cus.getAddress());
        stm.setObject(4, cus.getCity());
        stm.setObject(5, cus.getProvince());
        stm.setObject(6, cus.getPostalCode());
        stm.setObject(7, cus.getId());
        return stm.executeUpdate() > 0;
    }


    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE CustID='" + id + "'").executeUpdate() > 0;
    }

    public boolean searchCustomer(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE CustID=?");
        stm.setObject(1,id);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            Customer c1 = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
            setData(c1);
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Set", ButtonType.OK).show();
        }
        return true;
    }
    void setData(Customer c) {
        //c.getId(),c.getTitle(),c.getName(),c.getAddress(),c.getCity(),c.getProvince(), c.getPostalCode();

    }
}

