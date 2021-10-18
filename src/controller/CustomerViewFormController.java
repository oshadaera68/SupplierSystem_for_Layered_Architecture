package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerViewFormController {
    public void customerSaveOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/SaveForm.fxml"))));
        stage.setTitle("Save Form");
        stage.show();
    }

    public void customerSearchOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/SearchForm.fxml"))));
        stage.setTitle("Search Form");
        stage.show();
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/DeleteForm.fxml"))));
        stage.setTitle("Delete Form");
        stage.show();
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/UpdateForm.fxml"))));
        stage.setTitle("Update Form");
        stage.show();
    }

    public void customerSelectAllOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/SelectAllForm.fxml"))));
        stage.setTitle("Select All Form");
        stage.show();
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/PlaceOrderForm.fxml"))));
        stage.setTitle("Place Order Form");
        stage.show();
    }
}