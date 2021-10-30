package dao.Custom;

import dao.SuperDao;
import dto.CustomDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDao extends SuperDao {
    ArrayList<CustomDto> getOrderDetailsFromOrderID(String oId) throws SQLException, ClassNotFoundException;
}
