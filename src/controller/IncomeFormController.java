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

public class IncomeFormController {
    public ImageView imgBack;
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgDay;
    public ImageView imgMonth;
    public ImageView imgYear;
    public ImageView imgCustomer;
    public AnchorPane rootContext;

    public void initialize(){
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), rootContext);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void navigateToBack(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/views/ManageItemForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.rootContext.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manage Item Form | Supermarket System v0.1.0");
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgDay":
                    root = FXMLLoader.load(getClass().getResource("/views/DaliyIncomeForm.fxml"));
                    break;

                case "imgMonth":
                    root = FXMLLoader.load(getClass().getResource("/views/MonthlyIncomeForm.fxml"));
                    break;

                case "imgYear":
                    root = FXMLLoader.load(getClass().getResource("/views/YearlyIncomeForm.fxml"));
                    break;

                case "imgCustomer":
                    root = FXMLLoader.load(getClass().getResource("/views/CustomerWiseIncome.fxml"));
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.rootContext.getScene().getWindow();
                primaryStage.setTitle("System Reports | Supermarket System v0.1.0");
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
                case "imgDay":
                    lblMenu.setText("Daily Income");
                    lblDescription.setText("Daily income the system.");
                    break;

                case "imgMonth":
                    lblMenu.setText("Monthly Income");
                    lblDescription.setText("Monthly income in the system.");
                    break;

                case "imgYear":
                    lblMenu.setText("Yearly Income");
                    lblDescription.setText("Yearly income in the system.");
                    break;

                case "imgCustomer":
                    lblMenu.setText("Customers Income");
                    lblDescription.setText("Customers income in the system.");
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
            lblMenu.setText("Welcome Income Section");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }
}
