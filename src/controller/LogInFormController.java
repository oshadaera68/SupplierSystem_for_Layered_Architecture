package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.ValidationUtil;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class LogInFormController {
    public JFXTextField txtUser;
    public JFXButton btnLogIn;
    public JFXPasswordField passWord;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern userRegEx = Pattern.compile("^[A-z]{5}$");
    Pattern passRegEx = Pattern.compile("^[0-9]{4}$");

    public void initialize() {
        btnLogIn.setDisable(true);
        storeValidate();
    }

    private void storeValidate() {
        map.put(txtUser, userRegEx);
        map.put(passWord, passRegEx);
    }

    public void logInAction(ActionEvent actionEvent) throws IOException {
        String user = txtUser.getText();
        String pass = passWord.getText();
        if (user.equals("admin") && pass.equals("1234")) {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/WelcomeScreenForm.fxml"))));
            stage.setTitle("Welcome Screen");
            stage.show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Please Try Again...").show();
        }
    }

    public void txtFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnLogIn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                // new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();
            }
        }
    }
}