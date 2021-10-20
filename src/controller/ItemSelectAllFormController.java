package controller;


import dao.ItemDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import views.Tm.ItemTm;

import java.sql.Array;
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

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        try {

            ItemDaoImpl itemDao = new ItemDaoImpl();
            ArrayList<Item> allItems = itemDao.getAllItems();
            for (Item allItem : allItems) {
                tblItem.getItems().add(new ItemTm(allItem.getItemCode(), allItem.getDescription(), allItem.getPackSize(), allItem.getUnitPrice(), allItem.getQtyOnHand()));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}