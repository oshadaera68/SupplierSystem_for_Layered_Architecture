package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;
import util.ValidationUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class DeleteFormController {
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

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE CustID=?");
        stm.setObject(1, txtId.getText());
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

    boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE CustID='" + id + "'").executeUpdate() > 0;
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        if (delete(txtId.getText())) {
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