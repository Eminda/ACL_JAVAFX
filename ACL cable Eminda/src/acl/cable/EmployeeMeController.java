/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import UeserController.DBController;
import UeserController.Dialog;
import acl.cable.modal.comman.Employee;
import acl.cable.modal.comman.Engineer;
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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class EmployeeMeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private Button conform;
    @FXML private Button canlcel;
    @FXML PasswordField newP;
    @FXML PasswordField newAP;
    @FXML PasswordField oldP;
    @FXML
    private BorderPane borderPaneEmployee;
    @FXML AnchorPane passwordPane;
    DBController db;
    //public static boolean edit=false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setEmployeePanel();
            db = new DBController();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeMeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(EmployeeMeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public void setEmployeePanel() throws IOException {
        FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeViewMe.fxml"));
        fx.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("EmployeeViewMe.fxml").openStream());
        fx.<EmployeeViewMeController>getController().init(this);
        borderPaneEmployee.setCenter(pane);
        passwordPane.setVisible(false);
    }
    
    public void visiblePassword(){
        passwordPane.setVisible(true);
    }
    @FXML public void hidePassword(){
        
        newP.clear();
        newAP.clear();
        oldP.clear();
        passwordPane.setVisible(false);
    }
    @FXML public void buttonClickedEdit() throws RemoteException, NotBoundException{
        System.out.println(oldP.getText()+" "+newP.getText()+" "+newAP.getText()+" "+MainWindowController.currentPassword);
        if(oldP.getText().isEmpty()||newP.getText().isEmpty()||newAP.getText().isEmpty()){Dialog.showError("Please fill all three requirements");}
        else if (oldP.getText().equals(MainWindowController.currentPassword)){
            if (newP.getText().equals(newAP.getText())){
                db = new DBController();
                Engineer eng = new Engineer();
                eng.setEpfId(MainWindowController.loggUser.getEpfId());
                System.out.println(MainWindowController.loggUser.getEmplyId());
                eng.setPasseord(newP.getText());
                boolean result = db.resetPassword(eng);
                if(result) {
                Dialog.showInfo("Success", "The passwor is been changed successfuly");
                MainWindowController.loggUser.setPasseord(newP.getText());
                MainWindowController.currentPassword=newP.getText();
                }
                else Dialog.showError("Server Error, Try again later ");
                hidePassword();
            }
            else{Dialog.showError("New passwords are not matching");}
         }else{Dialog.showError("The password u entered is wrong ");}
    }
    
//    public void setCurentEditin(Employee emp) throws IOException{
//         FXMLLoader fx=new FXMLLoader(getClass().getClassLoader().getResource("EmployeeCurrentEditController.fxml"));
//        fx.setBuilderFactory(new JavaFXBuilderFactory());
//        AnchorPane pane=(AnchorPane)fx.load(getClass().getResource("EmployeeCurrentEditController.fxml").openStream());
//        fx.<EmployeeCurrentEditController>getController().moreDetails(emp);
//        borderPaneEmployee.setCenter(pane);
//        passwordPane.setVisible(false);
//    }
}
