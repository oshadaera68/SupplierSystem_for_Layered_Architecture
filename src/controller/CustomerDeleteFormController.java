package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;
import util.ValidationUtil;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class CustomerDeleteFormController {
    public JFXTextField txtId;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public JFXButton btnCusDelete;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idRegEx = Pattern.compile("^(C0-)[0-9]{3,4}$");

    public void initialize() {
        btnCusDelete.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtId, idRegEx);
    }

    public void searchCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String cusId = txtId.getText();
        Customer customer = new CustomerController().getCustomer(cusId);

        if (customer == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set", ButtonType.OK).showAndWait();
        } else {
            setData(customer);
        }

    }

    void setData(Customer c) {
        txtId.setText(c.getId());
        txtTitle.setText(c.getTitle());
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtAddress.setText(c.getCity());
        txtAddress.setText(c.getProvince());
        txtAddress.setText(c.getPostalCode());

    }


    public void deleteCustomerOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        if (new CustomerController().deleteCustomer(txtId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again", ButtonType.OK).show();
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
        Object response = ValidationUtil.validate(map, btnCusDelete);

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