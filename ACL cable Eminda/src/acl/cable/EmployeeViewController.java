/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import acl.cable.modal.comman.Employee;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
    public boolean edit;
    
    @FXML
    private BorderPane  borderView;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        edit = false;
    }    
    
    @FXML
    private void btnMeClicked(MouseEvent e) throws IOException{
        //EmployeeMeController.edit=true;
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeMe.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane pane=(BorderPane)fx.load(getClass().getResource("EmployeeMe.fxml"));
        EmployeeMeController ctrl = (EmployeeMeController)fx.getController();
        
    //ctrl.setEmployeePanel();
        pane.setPrefWidth(borderView.getWidth());
        pane.setPrefHeight(borderView.getHeight());
        borderView.setCenter(pane);
    }
    @FXML
    private void btnCurrentEmployeeClicked(MouseEvent e) throws IOException, RemoteException, NotBoundException{
       showCurrentTable();
    }
    @FXML
    private void btnResignedEmployeeClicked(MouseEvent e) throws IOException{
        showResignedTable();
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
    public void enableEdit(Employee emp,EmployeeViewController view) throws IOException{
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeCurrentEdit.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("EmployeeCurrentEdit.fxml").openStream());
        fx.<EmployeeCurrentEditController>getController().moreDetails(emp,view);
        pane.setPrefWidth(borderView.getWidth());
        pane.setPrefHeight(borderView.getHeight());
        borderView.setCenter(pane);
    }
    public void showCurrentTable() throws IOException, RemoteException, NotBoundException{
         FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeCurrent.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane pane=(BorderPane)fx.load(getClass().getResource("EmployeeCurrent.fxml").openStream());
        EmployeeCurrentController ctrl =fx.getController();
        ctrl.makeList();
        ctrl.initComponent(this);
        pane.setPrefWidth(borderView.getWidth());
        pane.setPrefHeight(borderView.getHeight());
        
        borderView.setCenter(pane);
    }
    
    public void showResignedTable() throws IOException{
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeResignedView.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        BorderPane pane=(BorderPane)fx.load(getClass().getResource("EmployeeResignedView.fxml").openStream());
        EmployeeResignedViewController ctrl =fx.getController();
        ctrl.setView(this);
        pane.setPrefWidth(borderView.getWidth());
        pane.setPrefHeight(borderView.getHeight());
        borderView.setCenter(pane);
    }
    
}
