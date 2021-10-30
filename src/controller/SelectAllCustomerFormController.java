package controller;

import bo.custom.BoFactory;
import bo.custom.CustomerBo;
import entity.Customer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.Tm.CustomerTm;

import java.io.IOException;
import java.net.URL;
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
    public AnchorPane rootContext;
    public ImageView imgBack;
    private final CustomerBo customerBo = (CustomerBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.CUSTOMER);

    public void initialize() {


        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colCustomerTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));

        try {

            ArrayList<Customer> allCustomers = customerBo.getAllCustomer();
            for (Customer allCustomer : allCustomers) {
                tblCustomer.getItems().add(new CustomerTm(allCustomer.getCustID(), allCustomer.getCusTitle(), allCustomer.getCustName(), allCustomer.getCustAddress(), allCustomer.getCity(), allCustomer.getProvince(), allCustomer.getPostalcode()));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void navigateToBack(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/views/CustomerViewForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.rootContext.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Customer View | Supermarket System v0.1.0");
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }
}