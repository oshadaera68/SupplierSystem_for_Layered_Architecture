package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminViewFormController {
    public void systemReportsOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/SystemReportsForm.fxml"))));
        stage.setTitle("System Reports");
        stage.show();
    }

    public void manageItemsOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/ManageItemForm.fxml"))));
        stage.setTitle("Manage Item");
        stage.show();
    }
}
