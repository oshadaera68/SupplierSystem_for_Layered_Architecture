package controller;

import db.DbConnection;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class CustomerViewFormController {
    public AnchorPane rootContext;
    public ImageView imgAdd;
    public ImageView imgBack;
    public ImageView imgCustomerSearch;
    public ImageView imgUpdate;
    public ImageView imgSelectAll;
    public ImageView imgDelete;
    public Label lblMenu;
    public Label lblDescription;

    public void initialize() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), rootContext);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) throws IOException {

        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome To Customer Section");
            lblDescription.setText("Please select one of above main operations to proceed");

        }
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "imgAdd":
                    lblMenu.setText("Add Customers");
                    lblDescription.setText("Add Customers in the system.");
                    break;

                case "imgUpdate":
                    lblMenu.setText("Update Customers");
                    lblDescription.setText("Update Customers in the system.");
                    break;

                case "imgDelete":
                    lblMenu.setText("Delete Customers");
                    lblDescription.setText("Delete Customers in the system.");
                    break;

                case "imgSelectAll":
                    lblMenu.setText("All Customers Table");
                    lblDescription.setText("All Customers On the table in the system.");
                    break;

                case "imgCustomerSearch":
                    lblMenu.setText("Search Customers");
                    lblDescription.setText("Search Customers in the system");
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

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgAdd":
                    root = FXMLLoader.load(getClass().getResource("/views/SaveCustomerForm.fxml"));
                    break;

                case "imgUpdate":
                    root = FXMLLoader.load(getClass().getResource("/views/UpdateCustomerForm.fxml"));
                    break;

                case "imgDelete":
                    root = FXMLLoader.load(getClass().getResource("/views/CustomerDeleteForm.fxml"));
                    break;

                case "imgSelectAll":
                    root = FXMLLoader.load(getClass().getResource("/views/SelectAllCustomerForm.fxml"));
                    break;

                case "imgCustomerSearch":
                    root = FXMLLoader.load(getClass().getResource("/views/SearchCustomerForm.fxml"));
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.rootContext.getScene().getWindow();
                primaryStage.setTitle("Manage Customer Form | Supermarket System v0.1.0");
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void navigateToBack(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/views/CashierForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.rootContext.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cashier Form | MINDARTLK Creations");
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void reportsOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("../views/report/Customer_Details.jrxml"));
            JasperReport report = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}