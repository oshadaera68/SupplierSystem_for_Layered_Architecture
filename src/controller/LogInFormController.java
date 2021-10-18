package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class LogInFormController {
    public JFXTextField txtUser;
    public JFXTextField txtPassword;

    public void logInAction(ActionEvent actionEvent) throws IOException {
        String user = txtUser.getText();
        String pass = txtPassword.getText();
        if (user.equals("admin") && pass.equals("1234")) {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/WelcomeScreenForm.fxml"))));
            stage.setTitle("Welcome Screen");
            stage.show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Please Try Again...").show();
        }
    }
}