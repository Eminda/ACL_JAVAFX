/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class EmployeeViewController implements Initializable {
    @FXML
    private Label lblHeading;
    @FXML
    private Button btnFake;
    @FXML
    private VBox vboxData;

    
    @FXML
    private BorderPane  borderView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void btnMeClicked(MouseEvent e) throws IOException{
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeMe.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane pane=(BorderPane)fx.load(getClass().getResource("EmployeeMe.fxml"));
        pane.setPrefWidth(borderView.getWidth());
        pane.setPrefHeight(borderView.getHeight());
        borderView.setCenter(pane);
    }
    @FXML
    private void btnCurrentEmployeeClicked(MouseEvent e) throws IOException{
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeCurrent.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane pane=(BorderPane)fx.load(getClass().getResource("EmployeeCurrent.fxml"));
        pane.setPrefWidth(borderView.getWidth());
        pane.setPrefHeight(borderView.getHeight());
        borderView.setCenter(pane);
    }
    @FXML
    private void btnResignedEmployeeClicked(MouseEvent e) throws IOException{
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeResignedView.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane pane=(BorderPane)fx.load(getClass().getResource("EmployeeResignedView.fxml"));
        pane.setPrefWidth(borderView.getWidth());
        pane.setPrefHeight(borderView.getHeight());
        borderView.setCenter(pane);
    }
    @FXML
    private void btnEmployeeCteateClicked(MouseEvent e) throws IOException{
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeCreateEmployee.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane pane=(BorderPane)fx.load(getClass().getResource("EmployeeCreateEmployee.fxml"));
        pane.setPrefWidth(borderView.getWidth());
        pane.setPrefHeight(borderView.getHeight());
        borderView.setCenter(pane);
    }
    @FXML
    private void btnBackClicked(MouseEvent e){
        MainWindowController.controller.setMainWindowControl2();
    }
}
