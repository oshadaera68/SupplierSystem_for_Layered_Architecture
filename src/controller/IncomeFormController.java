package controller;

import db.DbConnection;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class IncomeFormController {

    public AnchorPane rootContext;
    public ImageView imgBack;

    public void navigateToBack(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/views/SystemReportsForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.rootContext.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.setTitle("System Reports | SuperMarket System v0.1.0");
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void daliyIncomeOnAction(MouseEvent mouseEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/report/Daliy_Report.jrxml"));
            JasperReport report = JasperCompileManager.compileReport(load);
            JasperPrint print = JasperFillManager.fillReport(report, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(print, false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void monthlyIncomeOnAction(MouseEvent mouseEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/report/Monthly_Report.jrxml"));
            JasperReport report = JasperCompileManager.compileReport(load);
            JasperPrint print = JasperFillManager.fillReport(report, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(print, false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void annualyIncomeOnAction(MouseEvent mouseEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/report/Annualy_Report.jrxml"));
            JasperReport report = JasperCompileManager.compileReport(load);
            JasperPrint print = JasperFillManager.fillReport(report, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(print,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void customerWiseIncomeOnAction(MouseEvent mouseEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/report/Customer_Wise.jrxml"));
            JasperReport report = JasperCompileManager.compileReport(load);
            JasperPrint print = JasperFillManager.fillReport(report, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(print,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
