package controller;

import bo.custom.BoFactory;
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
import dto.CustomerDto;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class SaveCustomerFormController {
    public JFXTextField txtId;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public JFXButton btnSaveCus;
    public ImageView imgBack;
    public AnchorPane rootContext;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idRegEx = Pattern.compile("^(C00-)[0-9]{3,20}$");
    Pattern titleRegEx = Pattern.compile("^[A-z]{1,5}$");
    Pattern nameRegEx = Pattern.compile("^[A-z ]{4,30}$");
    Pattern addressRegEx = Pattern.compile("^[A-z\0-9 ]{5,30}$");
    Pattern cityRegEx = Pattern.compile("^[A-z]{4,20}$");
    Pattern provinceRegEx = Pattern.compile("^[A-z ]{4,20}$");
    Pattern postalCodeRegEx = Pattern.compile("^[0-9]{4,9}$");
    private final CustomerBo customerBo = (CustomerBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.CUSTOMER);
    public void initialize() {

        btnSaveCus.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtId, idRegEx);
        map.put(txtTitle, titleRegEx);
        map.put(txtName, nameRegEx);
        map.put(txtAddress, addressRegEx);
        map.put(txtCity, cityRegEx);
        map.put(txtProvince, provinceRegEx);
        map.put(txtPostalCode, postalCodeRegEx);
    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        CustomerDto c1 = new CustomerDto(
                txtId.getText(), txtTitle.getText(), txtName.getText(), txtAddress.getText(),
                txtCity.getText(), txtProvince.getText(), txtPostalCode.getText()
        );

        Customer customer = new Customer(c1.getId(), c1.getTitle(), c1.getName(), c1.getAddress(), c1.getCity(), c1.getProvince(), c1.getPostalCode());
        boolean addCustomer = customerBo.addCustomer(customer);

        if (addCustomer) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Data..", ButtonType.OK).showAndWait();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try again...", ButtonType.OK).showAndWait();
        }

        txtId.clear();
        txtTitle.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();

    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSaveCus);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                // new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }

    public void navigateToBack(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/views/CustomerViewForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.rootContext.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Customer View Form | SuperMarket System v0.1.0");
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }
}