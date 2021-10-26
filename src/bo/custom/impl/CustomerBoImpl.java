package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.Custom.CustomerDao;
import dao.DAOFactory;
import entity.Customer;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {
    private final CustomerDao customerDao = (CustomerDao) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.CUSTOMER);

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
