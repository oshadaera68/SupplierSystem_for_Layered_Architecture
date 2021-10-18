package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import views.Tm.ItemTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemSelectAllFormController {
    public TableView<ItemTm> tblItem;
    public TableColumn colItemCode;
    public TableColumn colDesc;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQty;

    public void initialize() {
        try {

            colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
            colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

            loadAllItems();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllItems() throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item");
        ResultSet rst = stm.executeQuery();
        ArrayList<Item> Items = new ArrayList<>();
        while (rst.next()) {
            Items.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            ));
        }
        setCustomersToTable(Items);
    }

    private void setCustomersToTable(ArrayList<Item> customers) {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        customers.forEach(e -> {
            obList.add(
                    new ItemTm(e.getItemCode(), e.getDescription(), e.getPackSize(), e.getUnitPrice(), e.getQtyOnHand()));
        });
        tblItem.setItems(obList);
    }

}