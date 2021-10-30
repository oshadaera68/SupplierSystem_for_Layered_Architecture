package bo.custom.impl;

import bo.custom.OrderBo;
import dao.Custom.OrderDao;
import dao.DAOFactory;
import entity.OrderDetail;
import entity.Orders;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBoImpl implements OrderBo {
    private final OrderDao orderDao = (OrderDao) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.ORDER);

    public String orderId() throws SQLException, ClassNotFoundException {
        return orderDao.orderId();
    }

    public boolean placeOrder(Orders orders, ArrayList<OrderDetail> orderDetails) throws SQLException, ClassNotFoundException {
        Boolean purchaseOrder = orderDao.purchaseOrder(orders, orderDetails);

        if (purchaseOrder) {
            new Alert(Alert.AlertType.CONFIRMATION, "Order Purchased").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        return false;
    }

    public boolean saveOrderDetails(String id, ArrayList<OrderDetail> details) throws SQLException, ClassNotFoundException {
        return orderDao.saveOrderDetails(id, details);
    }
}
