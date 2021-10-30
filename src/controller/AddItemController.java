package controller;


import bo.custom.BoFactory;
import bo.custom.ItemBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Item;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dto.ItemDto;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
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
    public ImageView imgBack;
    public AnchorPane rootContext;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern itemIdRegEx = Pattern.compile("^(I00-)[0-9]{3,20}$");
    Pattern descriptionRegEx = Pattern.compile("^[A-z ]{3,40}$");
    Pattern packSizeRegEx = Pattern.compile("^[A-z]{1,20}$");
    Pattern unitPriceRegEx = Pattern.compile("^[1-9][0-9][0-9]([.][0-9]{2})?$");
    Pattern qtyRegEx = Pattern.compile("^[0-9]{1,}$");
    private final ItemBo itemBo = (ItemBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ITEM);

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
        ItemDto i1 = new ItemDto(
                txtItemCode.getText(), txtDesc.getText(),
                txtPackSize.getText(), Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQty.getText())
        );

        Item item = new Item(i1.getItemCode(), i1.getDescription(), i1.getPackSize(), i1.getUnitPrice(), i1.getQtyOnHand());
        boolean addItem = itemBo.addItem(item);

        if (addItem) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Data..", ButtonType.OK).showAndWait();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try again...", ButtonType.OK).showAndWait();
        }

        txtItemCode.clear();
        txtDesc.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQty.clear();

    }

    public void txtFieldKeyReleased(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAdd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
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