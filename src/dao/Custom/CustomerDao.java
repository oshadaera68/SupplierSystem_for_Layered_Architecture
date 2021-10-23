package dao.Custom;

import dao.CrudDao;
import model.Customer;

import java.sql.SQLException;

public interface CustomerDao extends CrudDao<Customer, String> {
}
