package controller;

import bo.ItemBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Item;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateItemFormController {
    private final ItemBoImpl itemBo = new ItemBoImpl();
    public JFXTextField txtItemCode;
    public JFXTextField txtDesc;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnUpdate;
    public AnchorPane rootContext;
    public ImageView imgBack;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern itemIdRegEx = Pattern.compile("^(I00-)[0-9]{3,20}$");

    public void initialize() {
        btnUpdate.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtItemCode, itemIdRegEx);
    }

    public void searchItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String itemCode = txtItemCode.getText();
        Item item = itemBo.searchById(itemCode);

        if (item == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(item);
        }

    }

    public void updateItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1 = new Item(
                txtItemCode.getText(), txtDesc.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );

        Item item = new Item(i1.getItemCode(), i1.getDescription(), i1.getPackSize(), i1.getUnitPrice(), i1.getQtyOnHand());
        boolean updateItem = itemBo.updateItem(item);

        if (updateItem) {
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

    public void navigateToBack(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/views/ManageItemForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.rootContext.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Item View | Supermarket System v0.1.0");
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }
}