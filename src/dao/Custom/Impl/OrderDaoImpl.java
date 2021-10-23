package dao.Custom.Impl;

import bo.PurchaseOrderBoImpl;
import dao.Custom.OrderDao;
import javafx.scene.control.Alert;
import model.ItemDetails;
import model.Order;

import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDaoImpl implements OrderDao {

    private final PurchaseOrderBoImpl order = new PurchaseOrderBoImpl();

    public String OrderId() throws SQLException, ClassNotFoundException {
        return order.orderId();
    }

    public boolean placeOrder(Order o) {
        Boolean purchaseOrder = order.purchaseOrder(o);

        if (purchaseOrder) {
            new Alert(Alert.AlertType.CONFIRMATION, "Order Purchased").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }
        return false;
    }

    public boolean saveOrderDetails(String id, ArrayList<ItemDetails> details) throws SQLException, ClassNotFoundException {
        return order.saveOrderDetails(id, details);
    }

}