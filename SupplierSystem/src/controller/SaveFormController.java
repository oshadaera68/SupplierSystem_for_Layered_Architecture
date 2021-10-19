package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Customer;

import java.sql.*;

public class SaveFormController {
    public JFXTextField txtId;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer c1 = new Customer(
                txtId.getText(),txtTitle.getText(),txtName.getText(),txtAddress.getText(),
                txtCity.getText(),txtProvince.getText(),txtPostalCode.getText()
        );

        if (saveCustomer(c1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved Data..", ButtonType.OK).showAndWait();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try again...",ButtonType.OK).showAndWait();
        }
    }

    boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getId());
        stm.setObject(2,c.getTitle());
        stm.setObject(3,c.getName());
        stm.setObject(4,c.getAddress());
        stm.setObject(5,c.getCity());
        stm.setObject(6,c.getProvince());
        stm.setObject(7,c.getPostalCode());

        return stm.executeUpdate()>0;
    }

}