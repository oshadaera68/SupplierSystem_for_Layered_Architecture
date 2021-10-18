package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import model.Customer;

import java.sql.*;

public class DeleteFormController {
    public JFXTextField txtId;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;

    public void searchCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        PreparedStatement stm =
                DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE CustID=?");
        stm.setObject(1, txtId.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            Customer c1= new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
            setData(c1);
        }else{
            new Alert(Alert.AlertType.WARNING, "Empty Set", ButtonType.OK).show();
        }

    }

    void setData(Customer c){
        txtId.setText(c.getId());
        txtTitle.setText(c.getTitle());
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtAddress.setText(c.getCity());
        txtAddress.setText(c.getProvince());
        txtAddress.setText(c.getPostalCode());

    }

    boolean delete(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE CustID='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        if (delete(txtId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted",ButtonType.OK).show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again",ButtonType.OK).show();
        }

    }
}