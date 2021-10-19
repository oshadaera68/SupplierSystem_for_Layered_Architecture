package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Item;
import util.ValidationUtil;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class SearchItemController {
    public JFXTextField txtItemCode;
    public JFXTextField txtDesc;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnSearch;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern itemIdRegEx = Pattern.compile("^(I0-)[0-9]{3,4}$");

    public void initialize(){
        btnSearch.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtItemCode,itemIdRegEx);
    }

    public void searchItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemId = txtItemCode.getText();


        Item item = new ItemController().getItem(itemId);

        if (item == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set", ButtonType.OK).showAndWait();
        } else {
            setData(item);
        }
    }

    void setData(Item i) {
        txtItemCode.setText(i.getItemCode());
        txtDesc.setText(i.getDescription());
        txtPackSize.setText(i.getPackSize());
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
        txtQty.setText(String.valueOf(i.getQtyOnHand()));
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSearch);

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