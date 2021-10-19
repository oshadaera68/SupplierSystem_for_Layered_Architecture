package controller;

import db.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
            ResultSet rst = DbConnection.getInstance().getConnection().
                    prepareStatement("SELECT * FROM Item").executeQuery();
            List<String> ids= new ArrayList<>();
            while (rst.next()){
                ids.add(
                        rst.getString(1)
                );
            }
            return ids;

        }
}
