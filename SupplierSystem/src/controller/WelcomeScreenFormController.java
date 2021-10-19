package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreenFormController {
    public void adminOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/AdminViewForm.fxml"))));
        stage.setTitle("Customer View Form");
        stage.show();
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/CustomerViewForm.fxml"))));
        stage.setTitle("Admin View Form");
        stage.show();
    }
}
