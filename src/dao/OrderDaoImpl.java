package dao;

import controller.OrderController;
import db.DbConnection;
import model.ItemDetails;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl {
    public String OrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT OrderID FROM Orders ORDER BY OrderID DESC LIMIT 1").executeQuery();
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

    public boolean placeOrder(Order o){
        Connection con=null;
        try {
            con= DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm =con.
                    prepareStatement("INSERT INTO Orders VALUES(?,?,?,?,?)");


            stm.setObject(1, o.getOrderId());
            stm.setObject(2, o.getOrderDate());
            stm.setObject(3, o.getCusId());
            stm.setObject(4, o.getOrderTime());
            stm.setObject(5, o.getCost());

            if (stm.executeUpdate() > 0){
                if (new OrderController().saveOrderDetails(o.getOrderId(), o.getDetails())){
                    con.commit();
                    return true;
                }else{
                    con.rollback();
                    return false;
                }
            }else{
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
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
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO OrderDetail VALUES (?,?,?,?)");
            stm.setObject(1, temp.getItemCode());
            stm.setObject(2, id);
            stm.setObject(3, temp.getQtyForSell());
            stm.setObject(4, temp.getUnitPrice());

            if (stm.executeUpdate() > 0) {

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
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET qtyOnHand=(qtyOnHand-" + qty + ")WHERE code='" + code + "'");
        return stm.executeUpdate() > 0;
    }

}