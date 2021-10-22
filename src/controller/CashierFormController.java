package controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class CashierFormController {

    public AnchorPane rootContext;
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgCustomer;
    public ImageView imgPlaceOrder;
    public ImageView imgPayment;
    public ImageView imgBack;

    public void initialize() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), rootContext);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            Parent root = null;

            switch (icon.getId()) {
                case "imgCustomer":
                    root = FXMLLoader.load(getClass().getResource("/views/CustomerViewForm.fxml"));
                    break;

                case "imgPlaceOrder":
                    root = FXMLLoader.load(getClass().getResource("/views/PlaceOrderForm.fxml"));
                    break;

                case "imgPayment":
                    root = FXMLLoader.load(getClass().getResource("/views/PaymentForm.fxml"));
                    break;

            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.rootContext.getScene().getWindow();
                primaryStage.setTitle("Cashier Form | Supermarket System v0.1.0");
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "imgCustomer":
                    lblMenu.setText("Customers");
                    lblDescription.setText("Managing Customers in this System.");
                    break;

                case "imgPlaceOrder":
                    lblMenu.setText("Place Order");
                    lblDescription.setText("Placing a Orders in this System.");
                    break;

                case "imgPayment":
                    lblMenu.setText("Payment Sections");
                    lblDescription.setText("Payment in this system.");
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
            lblMenu.setText("Welcome To Supermarket System-Cashier Section");
            lblDescription.setText("Please select one of above main operations to proceed");

        }
    }

    public void navigateToBack(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/views/DashBoardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.rootContext.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Supermarket System v0.1.0 | MINDARTLK Creations");
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

}
