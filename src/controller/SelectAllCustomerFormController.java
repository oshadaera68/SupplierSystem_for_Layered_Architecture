package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import views.Tm.CustomerTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class SelectAllCustomerFormController {
    public TableView<CustomerTm> tblCustomer;
    public TableColumn colCustomerId;
    public TableColumn colCustomerTitle;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;

    public void initialize() {

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colCustomerTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));

        try {
            setCustomersToTable(new CustomerController().getAllCustomers());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setCustomersToTable(ArrayList<Customer> customers) {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        customers.forEach(e -> {
            obList.add(
                    new CustomerTm(e.getId(), e.getTitle(), e.getName(), e.getAddress(), e.getCity(), e.getProvince(), e.getPostalCode()));
        });
        tblCustomer.setItems(obList);
    }
}