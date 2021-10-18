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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddItemController {
    public JFXTextField txtItemCode;
    public JFXTextField txtDesc;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnAdd;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern itemIdRegEx = Pattern.compile("^(I0-)[0-9]{3,4}$");
    Pattern descriptionRegEx = Pattern.compile("^[A-z ]{3,40}$");
    Pattern packSizeRegEx = Pattern.compile("^[A-z]{1,20}$");
    Pattern unitPriceRegEx = Pattern.compile("^[1-9][0-9]([.][0-9]{2})?$");
    Pattern qtyRegEx = Pattern.compile("^[0-9]{1,}$");

    public void initialize() {
        btnAdd.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtItemCode, itemIdRegEx);
        map.put(txtDesc, descriptionRegEx);
        map.put(txtPackSize, packSizeRegEx);
        map.put(txtUnitPrice, unitPriceRegEx);
        map.put(txtQty, qtyRegEx);
    }

    public void addItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1 = new Item(
                txtItemCode.getText(), txtDesc.getText(),
                txtPackSize.getText(), Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQty.getText())
        );

        if (saveItem(i1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

        txtItemCode.clear();
        txtDesc.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQty.clear();

    }

    boolean saveItem(Item i) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Item VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, i.getItemCode());
        stm.setObject(2, i.getDescription());
        stm.setObject(3, i.getPackSize());
        stm.setObject(4, i.getUnitPrice());
        stm.setObject(5, i.getQtyOnHand());

        return stm.executeUpdate() > 0;
    }


    public void txtFieldKeyReleased(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAdd);

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