package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import model.ItemDetails;
import model.Order;
import views.Tm.CartTm;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaceOrderFormController {
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox<String> cmbCustomerIds;
    public JFXComboBox<String> cmbItemIds;
    public JFXTextField txtCusTitle;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtQty;

    public TableView<CartTm> tblCart;
    public TableColumn colItemCode;
    public TableColumn colDesc;
    public TableColumn colPackSi;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colQty;
    public TableColumn colTotal;
    public Label lblTotal;
    ObservableList<CartTm> obList = FXCollections.observableArrayList();


    public void initialize() {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSi.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAndTime();

        try {
            loadCustomerIds();
            loadItemIds();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData(newValue);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbItemIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData(newValue);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });


    }

    private void setItemData(String itemId) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem(itemId);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set", ButtonType.CLOSE).show();
        } else {
            txtDescription.setText(i1.getDescription());
            txtPackSize.setText(i1.getPackSize());
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
        }
    }

    private void setCustomerData(String cusId) throws SQLException, ClassNotFoundException {
        Customer c1 = new CustomerController().getCustomer(cusId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            txtCusTitle.setText(c1.getName());
            txtCusName.setText(c1.getName());
            txtCusAddress.setText(c1.getAddress());
            txtCity.setText(c1.getCity());
            txtProvince.setText(c1.getProvince());
            txtPostalCode.setText(c1.getPostalCode());
        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemId = new ItemController().getAllItemIds();
        cmbItemIds.getItems().addAll(itemId);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomerIds.getItems().addAll(customerIds);
    }

    private void loadDateAndTime() {
        //load date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        //load time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void billOnAction(ActionEvent actionEvent) {
        //
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double total = unitPrice * qty;

        if (qtyOnHand < qty) {
            new Alert(Alert.AlertType.WARNING, "Invalid Qty").show();
            return;
        }

        CartTm cartTm = new CartTm(cmbItemIds.getValue(), description, packSize, qtyOnHand, qty, unitPrice, total);

        int rowNum = isExists(cartTm);

        if (rowNum == -1) {
            obList.add(cartTm);
        } else {
            CartTm tempTm = obList.get(rowNum);
            CartTm tm = new CartTm(
                    tempTm.getCode(), tempTm.getDescription(), tempTm.getPackSize(), tempTm.getQty(), tempTm.getQtyOnHand(), unitPrice, total + tempTm.getTotal()
            );

            if (qtyOnHand < tempTm.getQty()) {
                new Alert(Alert.AlertType.WARNING, "Invalid Qty").show();
                return;
            }

            obList.remove(rowNum);
            obList.add(tm);
        }
        tblCart.setItems(obList);
        calculateCost();

    }

    private int isExists(CartTm tm) {

        for (int i = 0; i < obList.size(); i++) {
            if (tm.getCode().equals(obList.get(i).getCode())) {
                return i;
            }
        }
        return -1;
    }

    void calculateCost() {
        double ttl = 0;
        for (CartTm tm : obList) {
            ttl += tm.getTotal();
        }
        lblTotal.setText(ttl + "/=");
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {

        ArrayList<ItemDetails> details = new ArrayList<>();
        double total = 0;
        for (CartTm temp : obList) {
            total += temp.getTotal();
            details.add(new ItemDetails(temp.getCode(),
                    temp.getUnitPrice(),
                    temp.getQty()
            ));
        }

        Order order = new Order("O-001",lblDate.getText(),lblTime.getText(),total,cmbCustomerIds.getValue(),details);

        if (new OrderController().placeOrder(order)) {
            new Alert(Alert.AlertType.CONFIRMATION,"Success Order").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try again").show();
        }
    }

}