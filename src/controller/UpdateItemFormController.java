package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Item;
import util.ValidationUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtDesc;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnUpdate;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern itemIdRegEx = Pattern.compile("^(I0-)[0-9]{3,4}$");

    public void initialize() {
        btnUpdate.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtItemCode, itemIdRegEx);
    }

    public void searchItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE id=?");
        stm.setObject(1, txtItemCode.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            Item c1= new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            );
            setData(c1);
        }else{
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }

    }

    public void updateItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1 = new Item(
                txtItemCode.getText(), txtDesc.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );

        if (update(i1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

        txtItemCode.clear();
        txtDesc.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQty.clear();

    }
    boolean update(Item c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET CustTitle=?, CustName=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=? ");
        stm.setObject(1,c.getDescription());
        stm.setObject(2,c.getPackSize());
        stm.setObject(3,c.getUnitPrice());
        stm.setObject(4,c.getQtyOnHand());
        stm.setObject(5,c.getItemCode());
        return stm.executeUpdate()>0;
    }

    void setData(Item i) {
        txtItemCode.setText(i.getItemCode());
        txtDesc.setText(i.getDescription());
        txtPackSize.setText(i.getPackSize());
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
        txtQty.setText(String.valueOf(i.getQtyOnHand()));
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnUpdate);

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