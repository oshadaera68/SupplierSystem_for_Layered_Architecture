package bo;

import dao.Custom.CustomerDao;
import dao.Custom.Impl.CustomerDaoImpl;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDao.add(customer);
    }

    @Override
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDao.getAll();
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDao.update(customer);
    }

    @Override
    public Customer searchById(String cusId) throws SQLException, ClassNotFoundException {
        return customerDao.searchById(cusId);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }
}
