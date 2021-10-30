package dao.Custom.Impl;

import bo.custom.BoFactory;
import bo.custom.OrderBo;
import dao.Custom.OrderDao;
import db.DbConnection;
import entity.OrderDetail;
import entity.Orders;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {

    public Boolean purchaseOrder(Orders o, ArrayList<OrderDetail> orderDetails) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            boolean exu = CrudUtil.executeUpdate("INSERT INTO Orders VALUES(?,?,?,?,?)", o.getOrderID(), o.getOrderDate(), o.getCustID(), o.getOrderTime(), o.getCost());

            if (exu) {
                if (saveOrderDetails(o.getOrderID(), orderDetails)) {
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
    public boolean saveOrderDetails(String id, ArrayList<OrderDetail> details) throws SQLException, ClassNotFoundException {
        for (OrderDetail temp : details) {
            boolean update = CrudUtil.executeUpdate("INSERT INTO OrderDetail VALUES (?,?,?,?)", temp.getOrderID(),temp.getItemCode(),temp.getOrderQty(),temp.getPrice());

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
        return CrudUtil.executeUpdate("UPDATE Item SET qtyOnHand=(qtyOnHand - ?)WHERE ItemCode=?",qty,code);
    }
}
