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

public class ManageItemFormController {

    public AnchorPane rootContext;
    public ImageView imgDelete;
    public ImageView imgAdd;
    public ImageView imgUpdate;
    public ImageView imgSelectAll;
    public ImageView imgSearch;
    public Label lblMenu;
    public Label lblDescription;
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
                case "imgAdd":
                    root = FXMLLoader.load(getClass().getResource("/views/AddItemForm.fxml"));
                    break;

                case "imgUpdate":
                    root = FXMLLoader.load(getClass().getResource("/views/UpdateItemForm.fxml"));
                    break;

                case "imgDelete":
                    root = FXMLLoader.load(getClass().getResource("/views/DeleteItemForm.fxml"));
                    break;

                case "imgSelectAll":
                    root = FXMLLoader.load(getClass().getResource("/views/ItemSelectAllForm.fxml"));
                    break;

                case "imgSearch":
                    root = FXMLLoader.load(getClass().getResource("/views/SearchItemForm.fxml"));
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.rootContext.getScene().getWindow();
                primaryStage.setTitle("Manage Items(Admin Form) | Supermarket System v0.1.0");
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
                case "imgAdd":
                    lblMenu.setText("Add Items");
                    lblDescription.setText("Add an Item in system.");
                    break;

                case "imgUpdate":
                    lblMenu.setText("Update Items");
                    lblDescription.setText("Update an Item in System.");
                    break;

                case "imgDelete":
                    lblMenu.setText("Delete Items");
                    lblDescription.setText("Deleting an item in system.");
                    break;

                case "imgSelectAll":
                    lblMenu.setText("All Items Table");
                    lblDescription.setText("All Items Show On the Table in system.");
                    break;

                case "imgSearch":
                    lblMenu.setText("Search Items");
                    lblDescription.setText("Find an Items in system.");
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
            lblMenu.setText("Welcome To Manage Item Form");
            lblDescription.setText("Please select one of above main operations to proceed");

        }
    }

    public void navigateOnBack(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/views/AdminViewForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.rootContext.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Form | Supermarket System v0.1.0");
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void reportsOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("../views/Item_Details.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}