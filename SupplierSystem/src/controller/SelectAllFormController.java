package controller;

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

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();
        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()) {
            customers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        setCustomersToTable(customers);
    }

    private void setCustomersToTable(ArrayList<Customer>customers){
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        customers.forEach(e->{
            obList.add(
                    new CustomerTm(e.getId(),e.getTitle(),e.getName(),e.getAddress(),e.getCity(),e.getProvince(),e.getPostalCode()));
        });
        tblCustomer.setItems(obList);
    }

}
