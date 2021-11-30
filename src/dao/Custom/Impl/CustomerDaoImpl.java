package dao.Custom.Impl;

import dao.Custom.CustomerDao;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfig;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean add(Customer c) {
       /* c.setCustID(c.getCustID());
        c.setCusTitle(c.getCusTitle());
        c.setCustName(c.getCustName());
        c.setCustAddress(c.getCustAddress());
        c.setPostalcode(c.getPostalcode());
        c.setCity(c.getCity());
        c.setProvince(c.getProvince());
*/
        /*String hql = "INSERT INTO Customer VALUES(custID:=custID,"+"cusTitle=:cusTitle,"+"custName=:custName,"+"custAddress=:custAddress,"+"city=:city,"+"province=:province,"+"postalCode=:postalCode)";
        Query query = session.createQuery(hql);*/
        /* return CrudUtil.executeUpdate(, c.getCustID(), c.getCusTitle(), c.getCustName(), c.getCustAddress(), c.getCity(), c.getProvince(), c.getPostalcode());*/
        Session session = FactoryConfig.getInstance().getSession();
        Transaction trans1 = session.beginTransaction();
        session.save(c);
        trans1.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        /*Customer customer = new Customer();
        customer.setCustID(id);*/
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        /*session.delete(id);*/
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Customer c) {
        /* return CrudUtil.executeUpdate("UPDATE Customer SET CustTitle=?,CustName=?, CustAddress=?, City=?, Province=?, PostalCode=?  WHERE CustID=?", c.getCusTitle(), c.getCustName(), c.getCustAddress(), c.getCity(), c.getProvince(), c.getPostalcode(), c.getCustID());
         */
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
          String hql = "UPDATE Customer  SET cusTitle=:cusTitle," + "custName=:custName," + "custAddress=:custAddress," + "city=:city," + "province=:province," + " postalCode=:postalCode" + " WHERE custID=:custID";
        Query query = session.createQuery(hql);
        query.setParameter("cusTitle", c.getCusTitle());
        query.setParameter("custName", c.getCustName());
        query.setParameter("custAddress", c.getCustAddress());
        query.setParameter("city", c.getCity());
        query.setParameter("province", c.getProvince());
        query.setParameter("postalCode", c.getPostalcode());
        query.setParameter("custID", 1);

        if (query.executeUpdate() > 0) {
            System.out.println("Hureee!!!");
        } else {
            System.out.println("something happened...!");
        }
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public ArrayList<Customer> getAll() {
        /*ArrayList<Customer> customer = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        while (rst.next()) {
            customer.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7)));
        }
        return customer;*/
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Customer";
        Query query = session.createQuery(hql);
        List<Customer> result = query.list();

        for (Customer customer : result) {
            customer.getCustID();
            customer.getCusTitle();
            customer.getCustName();
            customer.getCustAddress();
            customer.getCity();
            customer.getProvince();
            customer.getPostalcode();
        }
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public Customer searchById(String id) {
       /* ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE CustID=?", id);
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
        }
        return null;*/
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "SELECT * FROM Customer WHERE custID=:custID";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(Customer.class);
        sqlQuery.setParameter("custID",1);
        List<Customer> result = sqlQuery.list();

        for (Customer c : result) {
            c.getCustID();
        }
        transaction.commit();
        session.close();
        return null;
    }
}