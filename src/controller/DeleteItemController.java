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

public class DeleteItemController {
    public JFXTextField txtItemCode;
    public JFXTextField txtDesc;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnDelete;

    public void searchItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PreparedStatement stm =
                DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE ItemCode=?");
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

    void setData(Item i) {
        txtItemCode.setText(i.getItemCode());
        txtDesc.setText(i.getDescription());
        txtPackSize.setText(i.getPackSize());
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
        txtQty.setText(String.valueOf(i.getQtyOnHand()));
    }

    boolean delete(String code) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE ItemCode='" + code + "'").executeUpdate() > 0;
    }

    public void deleteItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (delete(txtItemCode.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }
}