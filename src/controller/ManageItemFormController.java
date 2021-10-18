package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageItemFormController {
    public void addItemOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/AddItem.fxml"))));
        stage.setTitle("Save Item Form");
        stage.show();
    }

    public void updateItemFormOnAction(ActionEvent actionEvent) {
        /*Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/AddItem.fxml"))));
        stage.setTitle("Update  Form");
        stage.show();*/
    }

    public void deleteItemFormOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/DeleteItem.fxml"))));
        stage.setTitle("Delete Item Form");
        stage.show();
    }

    public void searchItemFormOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/SearchItem.fxml"))));
        stage.setTitle("Search Item Form");
        stage.show();
    }

    public void selectAllFormOnAction(ActionEvent actionEvent) {
       /* Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/AddItem.fxml"))));
        stage.setTitle("Save Form");
        stage.show();*/
    }
}
