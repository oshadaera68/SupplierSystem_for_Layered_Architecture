package dao.Custom.Impl;

import dao.Custom.QueryDao;
import dto.CustomDto;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDaoImpl implements QueryDao {

    @Override
    public ArrayList<CustomDto> getOrderDetailsFromOrderID(String oId) throws SQLException, ClassNotFoundException {
        ArrayList<CustomDto> customDtos = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("select o.oid,o.OrderDate,o.CustID,od.OrderID,od.itemCode,od.OrderQty,od.unitPrice from Orders o inner join OrderDetail od on o.OrderID=od.OrderID where o.OrderID=?;", oId);
        while (rst.next()) {
            customDtos.add(new CustomDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getDouble(6)));
        }
        return customDtos;
    }
}
