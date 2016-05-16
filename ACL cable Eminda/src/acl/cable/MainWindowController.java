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
    public static ElectricalDepartment elect = new ElectricalDepartment();
    
    public static Engineer loggUser = new Engineer("", "1199", "Isham Mohamed",null);
    
    

    public static Employee getLoggUser() {
        
        return loggUser;
    }

//    public static void setLoggUser(Employee loggUser) {
//        MainWindowController.loggUser = (Engineer) loggUser;
//    }
    
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
        loggUser.setNIC("942610340V");
        loggUser.setPreferedName("Isham123");
        loggUser.setDepartment(elect);
        elect.setName("Electrical");
        controller=this;
        setMainWindowControl2();
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
    
}
