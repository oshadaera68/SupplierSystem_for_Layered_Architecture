package dao.Custom.Impl;

import dao.Custom.ItemDao;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean add(Item i){
        /* return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?)", i.getItemCode(), i.getDescription(), i.getPackSize(), i.getUnitPrice(), i.getQtyOnHand());*/
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(i);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        /*return CrudUtil.executeUpdate("DELETE FROM Item WHERE ItemCode=?", id);*/
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.get(Item.class,id);
        session.delete(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Item i) {
        /* return CrudUtil.executeUpdate("UPDATE Item SET Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE ItemCode=?", i.getDescription(), i.getPackSize(), i.getUnitPrice(), i.getQtyOnHand(), i.getItemCode());*/
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(i.getItemCode());
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Item> getAll(){
       /* ArrayList<Item> allItems = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        while (rst.next()) {
            allItems.add(new Item(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getInt(5)));
        }
        return allItems;*/

        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Item");
        List<Item> result = query.list();
        for (Item i : result) {
            i.getItemCode();
            i.getDescription();
            i.getPackSize();
            i.getUnitPrice();
            i.getQtyOnHand();
        }
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public Item searchById(String id){
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

        String sql = "SELECT * FROM Item WHERE itemCode=:itemCode";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(Item.class);
        List<Item> result = sqlQuery.list();

        for (Item i : result) {
            i.setItemCode(id);
        }
        transaction.commit();
        session.close();
        return null;
    }
}