package controller;

import bo.custom.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.Customer;
import entity.Item;
import entity.OrderDetail;
import entity.Orders;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import views.Tm.CartTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PlaceOrderFormController {
    private final CustomerBo customerBo = (CustomerBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.CUSTOMER);
    private final OrderBo orderBo = (OrderBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.PURCHASEORDER);
    private final ItemBo itemBo = (ItemBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ITEM);
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
    public Label lblOrderId;
    public ImageView imgBack;
    public AnchorPane rootContext;
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
        setOrderId();

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

    private void setOrderId() {
        try {
            lblOrderId.setText(orderBo.orderId());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemData(String itemId) throws SQLException, ClassNotFoundException {
        Item i1 = itemBo.searchById(itemId);

        if (i1 == null) {
            /*new Alert(Alert.AlertType.WARNING, "Empty Result Set", ButtonType.CLOSE).show();*/
        } else {
            txtDescription.setText(i1.getDescription());
            txtPackSize.setText(i1.getPackSize());
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
        }
    }

    private void setCustomerData(String cusId) throws SQLException, ClassNotFoundException {

        Customer c1 = customerBo.searchById(cusId);
        if (c1 == null) {
            /*new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();*/
        } else {
            txtCusTitle.setText(c1.getCusTitle());
            txtCusName.setText(c1.getCustName());
            txtCusAddress.setText(c1.getCustAddress());
            txtCity.setText(c1.getCity());
            txtProvince.setText(c1.getProvince());
            txtPostalCode.setText(c1.getCity());
        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemBo.getAllItem();
        List<String> itemIds = new ArrayList<>();
        for (Item i : all) {
            itemIds.add(i.getItemCode());
        }
        cmbItemIds.getItems().addAll(itemIds);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {

        ArrayList<Customer> all = customerBo.getAllCustomer();
        List<String> customerIds = new ArrayList<>();
        for (Customer c : all) {
            customerIds.add(c.getCustID());
        }
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

    public void billOnAction(MouseEvent mouseEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("../views/report/Bill.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(load);

            String description = txtDescription.getText();
            String OrderId = lblOrderId.getText();
            int qty = Integer.parseInt(txtQty.getText());
            int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            String s = lblTotal.getText().split("/")[0];

            HashMap map = new HashMap();
            map.put("description", description);
            map.put("OrderId", OrderId);
            map.put("Qty", String.valueOf(qty));
            map.put("QtyOnHand", String.valueOf(qtyOnHand));
            map.put("UnitPrice", String.valueOf(unitPrice));
            map.put("Total", Double.parseDouble(s));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
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

    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ArrayList<OrderDetail> details = new ArrayList<>();
        double total = 0;
        for (CartTm temp : obList) {
            total += temp.getTotal();
            details.add(new OrderDetail(
                    lblOrderId.getText(), temp.getCode(), temp.getQty(), temp.getUnitPrice()
            ));
        }

        Orders orders = new Orders(lblOrderId.getText(), lblDate.getText(), cmbCustomerIds.getValue(), lblTime.getText(), total, details);

        boolean placeOrder = orderBo.placeOrder(orders, details);
        if (placeOrder) {
            new Alert(Alert.AlertType.CONFIRMATION, "Success Order").show();
        } else {
            //new Alert(Alert.AlertType.WARNING, "Try again").show();
        }
        cmbCustomerIds.getSelectionModel().clearSelection();
        cmbItemIds.getSelectionModel().clearSelection();
        txtCusTitle.clear();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtQty.clear();
        tblCart.getItems().clear();
        lblTotal.setText("0.00");

    }

    public void navigateToBack(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/views/CashierForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.rootContext.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cashier Form | MINDARTLK Creations");
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }
}