/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import UeserController.DBController;
import UeserController.Dialog;
import UeserController.ValidateEmployee;
import acl.cable.modal.comman.Employee;
import acl.cable.modal.comman.Engineer;
import acl.cable.modal.comman.OIC;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */



public class EmployeeViewMeController implements Initializable {
private EmployeeMeController parent;
    /**
     * Initializes the controller class.
     */

     @FXML TextField name;
     @FXML TextField prefName;
     @FXML TextField epf;
     @FXML TextField nic;
     @FXML TextField department;
     @FXML Label departmentttl;
     @FXML Label position;
    public boolean noFaultAtPref=true;
    public boolean noFaultAtname =true;
    @FXML public Button EditProfile;
    @FXML public Button EditPassword;
    @FXML public Button Done;
    @FXML public Button Cancel;
    private DBController db;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        back();
        setText();
        // TODO
    }    
    
    
    public void init(EmployeeMeController paren){
        this.parent = paren;
    try {
        db = new DBController();
    } catch (RemoteException ex) {
        Logger.getLogger(EmployeeViewMeController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NotBoundException ex) {
        Logger.getLogger(EmployeeViewMeController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
   @FXML public void EditPasword(){
       parent.visiblePassword();
   }
   @FXML public void EditProfileClicked(){
       EditPassword.setVisible(false);
       EditProfile.setVisible(false);
       Done.setVisible(true);
       Cancel.setVisible(true);
       name.setEditable(true);
       prefName.setEditable(true);
       epf.setDisable(true);
       nic.setDisable(true);
       department.setDisable(true);
       
   }
   
  @FXML public void editPasswordBtn(){
      parent.visiblePassword();
  }
   @FXML public void canclebtn(){
       back();
   }
    public void back(){
        setText();
       EditPassword.setVisible(true);
       EditProfile.setVisible(true);
       Done.setVisible(false);
       Cancel.setVisible(false);
       name.setEditable(false);
       prefName.setEditable(false);
       epf.setDisable(false);
       nic.setDisable(false);
       department.setDisable(false);
       
   }
    
    @FXML public void creatbuttn() throws RemoteException, NotBoundException{
        noFaultAtname = (ValidateEmployee.isnull(name));
         noFaultAtPref=checkPref();
         if(noFaultAtPref&&noFaultAtname){
                editDetails();
                back();
                Dialog.showSuccessEdit(MainWindowController.getLoggUser());
         }
    }
   public void editDetails() throws RemoteException{
       MainWindowController.getLoggUser().setName(name.getText());
    MainWindowController.getLoggUser().setPreferedName(prefName.getText());
       System.out.println(prefName.getText()+" guier  "+MainWindowController.getLoggUser().getPreferedName());
    db.editprofile(MainWindowController.getLoggUser(),MainWindowController.getLoggUser().getEpfId());
   }
   @FXML public void NameKeyReleased(){
       int length = name.getText().length();
        int pos =name.getCaretPosition();
        ValidateEmployee.validateFname(name);
        if(name.getText().length()==length)name.positionCaret(pos);
        else if(name.getText().length()>pos) name.positionCaret(pos-1);else 
        name.positionCaret(name.getText().length());
    }
    
   @FXML public void PrefNameKeyReleased(){
       int length = prefName.getText().length();
        int pos =prefName.getCaretPosition();
        ValidateEmployee.validateFpref(prefName);
        if(prefName.getText().length()==length)prefName.positionCaret(pos);
        else if(prefName.getText().length()>pos) prefName.positionCaret(pos-1);else 
        prefName.positionCaret(prefName.getText().length());
    }
   
   @FXML public void pref(){
        if(!noFaultAtPref){
       if (prefName.getText().equals("INVALIED ENTRY")) prefName.setText(""); 
        prefName.setStyle(null);
        noFaultAtPref=true;
        }
    }
   
   @FXML public void name(){
        if(!noFaultAtname){
        if (name.getText().equals("INVALIED ENTRY")) name.setText(""); 
        name.setStyle(null);
        noFaultAtname=true;
        }}
   
   private void setText(){
       Employee emp = MainWindowController.getLoggUser();
       if (emp instanceof Engineer){
           Engineer eng = (Engineer) emp;
           name.setText(eng.getName());
           prefName.setText(eng.getPreferedName());
           nic.setText(eng.getNIC());
           epf.setText(eng.getEpfId());
           department.setText(eng.getDepartment().getName());
           departmentttl.setText("Department");
           position.setText("Engineer");
       }
       else if (emp instanceof OIC){
       OIC oic = (OIC) emp;
           name.setText(oic.getName());
           prefName.setText(oic.getPreferedName());
           nic.setText(oic.getNIC());
           epf.setText(oic.getEpfId());
           department.setText(oic.getFactor().getName());
           departmentttl.setText("Division");
           position.setText("OIC");
       }
       
   }
   private boolean checkPref() throws RemoteException, NotBoundException{
       if (prefName.getText() == null ? MainWindowController.getLoggUser().getPreferedName() == null : prefName.getText().equals(MainWindowController.getLoggUser().getPreferedName()))return true;
       else return ValidateEmployee.validatePref(prefName.getText(), prefName);
   }
}
