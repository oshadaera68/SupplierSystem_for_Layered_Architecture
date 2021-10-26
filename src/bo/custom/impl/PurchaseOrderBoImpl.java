package bo.custom.impl;

import bo.custom.PurchaseOrderBo;
import dao.Custom.OrderDao;
import dao.DAOFactory;
import db.DbConnection;
import entity.OrderDetail;
import entity.Orders;
import model.OrderDetailsDto;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBoImpl implements PurchaseOrderBo {
    private final OrderDao orderDao = (OrderDao) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.ORDER);

    public Boolean purchaseOrder(Orders o, ArrayList<OrderDetail> orderDetails) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            boolean exu = CrudUtil.executeUpdate("INSERT INTO Orders VALUES(?,?,?,?,?)", o.getOrderID(), o.getOrderDate(), o.getCustID(), o.getOrderTime(), o.getCost());

            OrderDetail orderDetail = new OrderDetailsDto();


            if (exu) {
                if (orderDao.saveOrderDetails(o.getOrderID(),o.getDetails())) {
                    con.commit();
                    return true;
                } else {
                    con.rollback();
                    return false;
                }
            } else {
                con.rollback();
                return false;
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

  @Override
    public ArrayList<OrderDetail> saveOrderDetails(String id, ArrayList<OrderDetail> orderDetail) throws SQLException, ClassNotFoundException {
        for (OrderDetail temp :orderDetail) {
            boolean update = CrudUtil.executeUpdate("INSERT INTO OrderDetail VALUES (?,?,?,?)", temp.getItemCode(), id, temp.getOrderQty(), temp.getDiscount());

            if (update) {
                if (updateQty(temp.getItemCode(), temp.getOrderQty())) {
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }


    public String orderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT OrderID FROM Orders ORDER BY OrderID DESC LIMIT 1");
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "O-00" + tempId;
            } else if (tempId <= 99) {
                return "O-0" + tempId;
            } else {
                return "O-" + tempId;
            }
        } else {
            return "O-001";
        }
    }

    private boolean updateQty(String code, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET qtyOnHand=(qtyOnHand-" + qty + ")WHERE code='" + code + "'", code, qty);
    }

}
