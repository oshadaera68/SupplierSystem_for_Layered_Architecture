package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.CustomerDaoImpl;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;
import util.ValidationUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class SaveFormController {
    public JFXTextField txtId;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public JFXButton btnSaveCus;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idRegEx = Pattern.compile("^(C00-)[0-9]{3,20}$");
    Pattern titleRegEx = Pattern.compile("^[A-z]{1,5}$");
    Pattern nameRegEx = Pattern.compile("^[A-z ]{4,30}$");
    Pattern addressRegEx = Pattern.compile("^[A-z\0-9 ]{6,30}$");
    Pattern cityRegEx = Pattern.compile("^[A-z]{4,20}$");
    Pattern provinceRegEx = Pattern.compile("^[A-z ]{4,20}$");
    Pattern postalCodeRegEx = Pattern.compile("^[0-9]{4,9}$");

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

        Customer c1 = new Customer(
                txtId.getText(), txtTitle.getText(), txtName.getText(), txtAddress.getText(),
                txtCity.getText(), txtProvince.getText(), txtPostalCode.getText()
        );
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        Customer customer = new Customer(c1.getId(), c1.getTitle(), c1.getName(), c1.getAddress(), c1.getCity(), c1.getProvince(), c1.getPostalCode());
        boolean addCustomer = customerDao.addCustomer(customer);

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
}