/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import acl.cable.modal.comman.ElectricalDepartment;
import acl.cable.modal.comman.Employee;
import acl.cable.modal.comman.Engineer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Eminda
 */
public class MainWindowController implements Initializable {
public static String[] dep = {"Electrical","Mechanical","AF","CCF","FF","DRF","RMF","S&B","DY"};
public static Employee loggUser;
public static String currentPassword;
public static int Currentid;

    
    public static MainWindowController controller;
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private BorderPane pnlView;
    

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
       
        setLogin();
        controller = this;
    } catch (IOException ex) {
        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    
    
    public void setPanelToCener(Node  node){
        pnlView.setCenter(node);
    }
    public void setMainWindowControl2(){
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("MainWindow2.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        try {
            AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("MainWindow2.fxml"));
            pnlView.setCenter(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setLogin() throws IOException{
         FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane = null ;
       
            pane = (AnchorPane)fx.load(getClass().getResource("Login.fxml").openStream());
            LoginController lg= (LoginController)fx.getController();
            lg.setMainWindow(this);
            pnlView.setCenter(pane);
    }
    
}
