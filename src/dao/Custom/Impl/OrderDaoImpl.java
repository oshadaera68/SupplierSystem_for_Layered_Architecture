package dao.Custom.Impl;

import bo.custom.impl.PurchaseOrderBoImpl;
import dao.Custom.OrderDao;
import dao.DAOFactory;
import entity.OrderDetail;
import entity.Orders;
import javafx.scene.control.Alert;
import model.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDaoImpl implements OrderDao{

    OrderDto orderDto = new OrderDto();

    private final PurchaseOrderBoImpl order = (PurchaseOrderBoImpl) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.ORDER);

    public String orderId() throws SQLException, ClassNotFoundException {
        return order.orderId();
    }

    public boolean PlaceOrder(Orders orders, ArrayList<OrderDetail> orderDetails) throws SQLException, ClassNotFoundException {
        Boolean purchaseOrder = order.purchaseOrder(orders,orderDetails);

        if (purchaseOrder) {
            new Alert(Alert.AlertType.CONFIRMATION, "Order Purchased").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }
        return false;
    }

    public ArrayList<OrderDetail> saveOrderDetails(String id, ArrayList<OrderDetail> details) throws SQLException, ClassNotFoundException {
        return order.saveOrderDetails(id,details);
    }

}