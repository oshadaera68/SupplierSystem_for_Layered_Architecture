package controller;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
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
    private final CustomerDao customerDao = new CustomerDaoImpl();

    public void initialize() {

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colCustomerTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));

        try {


            ArrayList<Customer> allCustomers = customerDao.getAllCustomers();
            for (Customer allCustomer : allCustomers) {
                tblCustomer.getItems().add(new CustomerTm(allCustomer.getId(), allCustomer.getTitle(), allCustomer.getName(), allCustomer.getAddress(), allCustomer.getCity(), allCustomer.getProvince(), allCustomer.getPostalCode()));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}