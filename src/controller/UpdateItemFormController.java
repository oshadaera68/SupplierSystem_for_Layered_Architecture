package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtDesc;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnUpdate;

    public void searchItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE ItemCode=?");
        stm.setObject(1, txtItemCode.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            Item i1 = new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            );
            setData(i1);
        } else {
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
    }

    boolean update(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET Description=?, PackSize=?, UnitPrice=?,QtyOnHand=? WHERE ItemCode=?");
        stm.setObject(1, i.getDescription());
        stm.setObject(2, i.getPackSize());
        stm.setObject(3, i.getUnitPrice());
        stm.setObject(4, i.getQtyOnHand());
        stm.setObject(5, i.getItemCode());
        return stm.executeUpdate() > 0;

    }

    void setData(Item i) {
        txtItemCode.setText(i.getItemCode());
        txtDesc.setText(i.getDescription());
        txtPackSize.setText(i.getPackSize());
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
        txtQty.setText(String.valueOf(i.getQtyOnHand()));
    }

}
