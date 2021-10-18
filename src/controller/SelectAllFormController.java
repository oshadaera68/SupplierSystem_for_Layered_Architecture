package controller;

import DAO.CustomerDaoImpl;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import views.Tm.CustomerTm;

import java.sql.*;
import java.util.ArrayList;

public class SelectAllFormController {
    public TableView<CustomerTm> tblCustomer;
    public TableColumn colCustomerId;
    public TableColumn colCustomerTitle;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;

    public void initialize() {

        try {

            colCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustId"));
            colCustomerTitle.setCellValueFactory(new PropertyValueFactory<>("CustTitle"));
            colCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustName"));
            colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("CustAddress"));
            colCity.setCellValueFactory(new PropertyValueFactory<>("City"));
            colProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
            colPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));

            loadAllCustomers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    private void loadAllCustomers() throws ClassNotFoundException, SQLException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        ArrayList<Customer> allCustomers = customerDao.getAllCustomers();
        for (Customer allCustomer : allCustomers) {
            new CustomerTm(allCustomer.getId(), allCustomer.getTitle(), allCustomer.getName(), allCustomer.getAddress(), allCustomer.getCity(), allCustomer.getProvince(), allCustomer.getPostalCode());
        }
    }
}
