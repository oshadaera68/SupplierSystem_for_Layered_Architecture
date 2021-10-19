package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import views.Tm.ItemTm;

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

            setItemsToTable(new ItemController().getAllItems());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setItemsToTable(ArrayList<Item> items) {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        items.forEach(e -> {
            obList.add(
                    new ItemTm(e.getItemCode(), e.getDescription(), e.getPackSize(), e.getUnitPrice(), e.getQtyOnHand()));
        });
        tblItem.setItems(obList);
    }
}