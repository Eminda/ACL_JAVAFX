/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import UeserController.DBController;
import UeserController.Dialog;
import acl.cable.modal.comman.ElectricalDepartment;
import acl.cable.modal.comman.Employee;
import acl.cable.modal.comman.Engineer;
import acl.cable.modal.comman.Factory;
import acl.cable.modal.comman.MechanicalDepartment;
import acl.cable.modal.comman.OIC;
import com.sun.jmx.snmp.BerDecoder;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


    
/**
 *
 * @author HP
 */
public class LoginController implements Initializable {

    @FXML Button login;
    @FXML TextField userName;
    @FXML TextField password;
    DBController DBC;
    ArrayList<String> details;
    private Employee emp;
    private MainWindowController mwc;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            DBC = new DBController();
        } catch (RemoteException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean checkUser() throws RemoteException{
        details = DBC.checkUser(userName.getText(), password.getText());
        if (details==null){
        Dialog.showError("Invalied username or password");
        return false;
        }return true;
    }
    
    public void setMainWindow(MainWindowController mwc){
        this.mwc = mwc;
    }
    public void setUser(){
        
        if(Integer.parseInt(details.get(0))<3){
            
             Engineer eng = new Engineer();
            if (Integer.parseInt(details.get(0))==1 ){
                ElectricalDepartment ec = new ElectricalDepartment();
                ec.setId(1);
                eng.setDepartment(ec);
             }
            if (Integer.parseInt(details.get(0))==2 ){
                MechanicalDepartment ec = new MechanicalDepartment();
                ec.setId(2);
                eng.setDepartment(ec);
             }
            eng.setPasseord(password.getText());
            emp=eng;
        }else{
            OIC oic = new OIC();
           Factory fc = new Factory();
           fc.setID(Integer.parseInt(details.get(0)));
           emp =oic;
        }
            emp.setEpfId(details.get(1));
            emp.setNIC(details.get(2));
            emp.setName(details.get(3));
            emp.setPreferedName(details.get(4));
        
        
            
    }
    @FXML public void ButtonClicked() throws RemoteException{
        if (checkUser()){
            setUser();
            MainWindowController.loggUser = emp;
            MainWindowController.currentPassword = password.getText();
            MainWindowController.Currentid = Integer.parseInt(details.get(0));
            System.out.println(MainWindowController.loggUser.getNIC()+" "+MainWindowController.currentPassword+" "+MainWindowController.Currentid);
            mwc.setMainWindowControl2();
        }
    }
    
   
}
