package controller;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

public class DashBoardFormController {
    public Label lblDate;
    public Label lblTime;
    public AnchorPane rootContext;
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgAdmin;
    public ImageView imgCashier;
    public ImageView imgIcon;

    public void initialize() {
        loadDateAndTime();
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), rootContext);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    private void loadDateAndTime() {
        //load date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        //load time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "imgAdmin":
                    lblMenu.setText("Admin Form");
                    lblDescription.setText("Add Items and Manage Reports.");
                    break;
                case "imgCashier":
                    lblMenu.setText("Cashier's Form");
                    lblDescription.setText("Add Customers and Order Placing.");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome To SuperMarket System");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgAdmin":
                    root = FXMLLoader.load(getClass().getResource("/views/AdminViewForm.fxml"));
                    break;
                case "imgCashier":
                    root = FXMLLoader.load(getClass().getResource("/views/CashierForm.fxml"));
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.rootContext.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void aboutUsOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/AboutUsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setFullScreen(false);
        stage.setTitle("About Us | Supermarket System v0.1.0 | MINDARTLK");
        stage.setResizable(false);
        stage.show();
    }

    public void exitOnAction(MouseEvent mouseEvent) {
        ButtonType buttonType = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonType1 = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Did you exit the system?", buttonType, buttonType1);
        alert.setTitle("Confirmation");
        Optional<ButtonType> close = alert.showAndWait();
        if (close.orElse(buttonType1) == buttonType) {
            Platform.exit();
            System.exit(0);
        }
    }
}