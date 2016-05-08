/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Isura Manchanayake
 */
public class MainWindow2Controller implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label label;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private GridPane grid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btn1.getStylesheets().add("buttonStyleSheet.css");

        grid.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                btn1.setPrefWidth(grid.getWidth() / 3.5);
                btn2.setPrefWidth(grid.getWidth() / 3.5);
                btn3.setPrefWidth(grid.getWidth() / 3.5);
                btn4.setPrefWidth(grid.getWidth() / 3.5);
                btn5.setPrefWidth(grid.getWidth() / 3.5);
                btn6.setPrefWidth(grid.getWidth() / 3.5);
            }
        });

        grid.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                btn1.setPrefHeight(grid.getHeight() / 2.5);
                btn2.setPrefHeight(grid.getHeight() / 2.5);
                btn3.setPrefHeight(grid.getHeight() / 2.5);
                btn4.setPrefHeight(grid.getHeight() / 2.5);
                btn5.setPrefHeight(grid.getHeight() / 2.5);
                btn6.setPrefHeight(grid.getHeight() / 2.5);
            }
        });

    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public void btnFaultReportClicked() throws IOException {
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("FaultReport.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = (AnchorPane) fx.load(getClass().getResource("FaultReport.fxml"));
        MainWindowController.controller.setPanelToCener(pane);
    }

    public void btnMaintainanceReportClicked() {

    }

    public void btnReportClicked() throws IOException {
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("ReportView.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = (AnchorPane) fx.load(getClass().getResource("ReportView.fxml"));
        
        MainWindowController.controller.setPanelToCener(pane);
    }

    public void btnMailClicked() {

    }

    public void btnUserControllerClicked() throws IOException {
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("EmployeeView.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane pane = (BorderPane) fx.load(getClass().getResource("EmployeeView.fxml"));
        MainWindowController.controller.setPanelToCener(pane);
    }

    public void btnMachineManageClicked() throws IOException {
        FXMLLoader fx = new FXMLLoader(getClass().getClassLoader().getResource("MachineManage.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = (AnchorPane) fx.load(getClass().getResource("MachineManage.fxml"));
        
        MainWindowController.controller.setPanelToCener(pane);
    }
}
