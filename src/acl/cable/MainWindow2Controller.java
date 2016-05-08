/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Isura Manchanayake
 */
public class MainWindow2Controller implements Initializable {

    public static MainWindow2Controller controller;
    
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
        controller = this;
        btn5.getStylesheets().add("buttonStyleSheet.css");

        addListeners();

    }
    
    public void addListeners() {
        
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
        Parent newWindow = FXMLLoader.load(getClass().getResource("FaultReport.fxml"));
        Scene newScene = new Scene(newWindow);
        Stage mainWindow = (Stage) (btn1).getScene().getWindow();
        double width = mainWindow.getWidth();
        double height = mainWindow.getHeight();
        mainWindow.setScene(newScene);
        mainWindow.setWidth(width);
        mainWindow.setHeight(height);
    }

    public void btnMaintainanceReportClicked() {

    }

    public void btnReportClicked() {

    }

    public void btnMailClicked() {

    }

    public void btnUserControllerClicked() {

    }

    public void btnLogClicked() {

    }
}
