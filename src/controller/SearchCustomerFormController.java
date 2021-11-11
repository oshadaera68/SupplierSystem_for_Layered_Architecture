package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Customer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class SearchCustomerFormController {

    public JFXTextField txtId;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public JFXButton btnSearchCustomer;
    public AnchorPane rootContext;
    public ImageView imgBack;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idRegEx = Pattern.compile("^(C00-)[0-9]{3,20}$");
    private final CustomerBo customerBo = (CustomerBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.CUSTOMER);

    public void initialize() {
        btnSearchCustomer.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtId, idRegEx);
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String cusId = txtId.getText();
        Customer customer = customerBo.searchById(cusId);
        if (customer == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set", ButtonType.OK).showAndWait();
        } else {
            setData(customer);
        }
    }

    void setData(Customer c) {
        txtId.setText(c.getCustID());
        txtTitle.setText(c.getCusTitle());
        txtName.setText(c.getCustName());
        txtAddress.setText(c.getCustAddress());
        txtCity.setText(c.getCity());
        txtProvince.setText(c.getProvince());
        txtPostalCode.setText(c.getPostalcode());
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSearchCustomer);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
            }
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