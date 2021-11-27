package dao.Custom.Impl;

import dao.Custom.ItemDao;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.CrudUtil;
import util.FactoryConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean add(Item i) throws SQLException, ClassNotFoundException {
       /* return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?)", i.getItemCode(), i.getDescription(), i.getPackSize(), i.getUnitPrice(), i.getQtyOnHand());*/
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(i);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*return CrudUtil.executeUpdate("DELETE FROM Item WHERE ItemCode=?", id);*/
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(id);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Item i) throws SQLException, ClassNotFoundException {
       /* return CrudUtil.executeUpdate("UPDATE Item SET Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE ItemCode=?", i.getDescription(), i.getPackSize(), i.getUnitPrice(), i.getQtyOnHand(), i.getItemCode());*/
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(i);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        while (rst.next()) {
            allItems.add(new Item(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getInt(5)));
        }
        return allItems;
    }

    @Override
    public Item searchById(String id) throws SQLException, ClassNotFoundException {
        /*ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE ItemCode=?", id);
        if (rst.next()) {
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            );

        }*/
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();

        return null;
    }
}