package dao.Custom.Impl;

import dao.Custom.OrderDao;
import model.ItemDetails;
import model.Order;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    public String OrderId() throws SQLException, ClassNotFoundException {
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

    public boolean placeOrder(Order o) {
        Connection con = null;
        try {
            boolean exu = CrudUtil.executeUpdate("INSERT INTO Orders VALUES(?,?,?,?,?)", o.getOrderId(), o.getOrderDate(), o.getCusId(), o.getOrderTime(), o.getCost());
            con.setAutoCommit(false);

            if (exu) {
                if (saveOrderDetails(o.getOrderId(), o.getDetails())) {
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

    public boolean saveOrderDetails(String id, ArrayList<ItemDetails> details) throws SQLException, ClassNotFoundException {
        for (ItemDetails temp : details) {
            boolean update = CrudUtil.executeUpdate("INSERT INTO OrderDetail VALUES (?,?,?,?)", temp.getItemCode(), id, temp.getQtyForSell(), temp.getUnitPrice());

            if (update) {
                if (updateQty(temp.getItemCode(), temp.getQtyForSell())) {
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String code, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET qtyOnHand=(qtyOnHand-" + qty + ")WHERE code='" + code + "'", code, qty);
    }

}