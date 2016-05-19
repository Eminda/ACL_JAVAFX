/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import UeserController.DBController;
import UeserController.Dialog;
import UeserController.ValidateEmployee;
import acl.cable.modal.comman.ElectricalDepartment;
import acl.cable.modal.comman.Employee;
import acl.cable.modal.comman.Engineer;
import acl.cable.modal.comman.Factory;
import acl.cable.modal.comman.MechanicalDepartment;
import acl.cable.modal.comman.OIC;
import acl.cable.modal.comman.Worker;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class EmployeeCurrentEditController implements Initializable {
    @FXML
    private Button editProfile;
    @FXML private Button cancle;
    @FXML private Button done;
    
    
    
    @FXML
    private Button resign;
    @FXML private TextField engName;
    @FXML private TextField engEpfno;
    @FXML private TextField engPrefName;
    @FXML private TextField engNic;
    @FXML private RadioButton isElec;
    public boolean noFaultAtEpf=true;
    public boolean noFaultAtNic=true;
    public boolean noFaultAtPref=true;
    public boolean noFaultAtname =true;
    private Employee emp;
    @FXML Label department;
    @FXML Label hPosition;
    @FXML ComboBox slectDeparment;
    @FXML Label position;
    DBController dbc;
    public EmployeeViewController view;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Called");
        cancle.setVisible(false);
        done.setVisible(false);
        try {
            dbc = new DBController();
        } catch (RemoteException ex) {
            Logger.getLogger(EmployeeCurrentEditController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(EmployeeCurrentEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public void moreDetails(Employee emp,EmployeeViewController view){
        this.emp = emp;
        System.out.println("@#$@$#@Called"+emp.getName());
        if(!authorize()) editProfile.setDisable(true);
        setDetails();
        this.view = view;
    }
    
    
    public void setDetails(){
        this.engName.setText(emp.getName());
        this.engPrefName.setText(emp.getPreferedName());
        System.out.println(engPrefName.getText());
        this.engNic.setText(emp.getNIC().substring(0,9));
        this.engEpfno.setText(emp.getEpfId());
        System.out.println("called"+engName.getText());
        if (emp instanceof Engineer) setEngineer();
        else if(emp instanceof OIC) setOIC();
        else setWorker();
        engEpfno.setStyle(null);
        engName.setStyle(null);
        engNic.setStyle(null);
        engPrefName.setStyle(null);
    }
    
    private void setEngineer(){
            Engineer eng =(Engineer)emp; 
            this.department.setText("Department");
            this.slectDeparment.getItems().addAll(
                    "Electrical",
                    "Mechanical"
            );
            
            slectDeparment.getSelectionModel().select(eng.getDepartment().getId()-1);
            position.setText("Engineer");
            
        }
    
     private void setWorker(){
            Worker eng =(Worker)emp; 
            this.department.setText("Department");
            this.slectDeparment.getItems().addAll(
                    "Electrical",
                    "Mechanical"
            );
            
            slectDeparment.getSelectionModel().select(eng.getDepartment().getId()-1);
            position.setText("Worker");
            
        }
    
     private void setOIC(){
            OIC eng =(OIC)emp; 
            this.department.setText("Division");
            this.slectDeparment.getItems().addAll(
                    "AFF",
                    "BBF",
                    "PPF",
                    "CCF"
                    
            );
            
            slectDeparment.getSelectionModel().select(eng.getFactor().getID());
            position.setText("OIC");
            
            
        }
     
     @FXML public void buttonCLickedDone() throws RemoteException, NotBoundException{
         proceedWithEditing();
         
         
         //buttonCLickedCalnce();
     }
     
     @FXML public void ResignedClicked() throws RemoteException, NotBoundException, IOException{
         System.out.println("RESIGN call 1    daefsef"+ emp.isResigned()); 
         dbc.resignEmployee(emp.getEpfId());
         view.showCurrentTable();
          System.out.println("RESIGN call 1");
          Dialog.showInfo("success", "Employee has been added to resigned list");
     }
     @FXML public void buttonCLickedCalnce(){
         engEpfno.setEditable(false);
         engName.setEditable(false);
         engNic.setEditable(false);
         engPrefName.setEditable(false);
         editProfile.setVisible(true);
         resign.setVisible(false);
         done.setVisible(false);
         cancle.setVisible(false);
         slectDeparment.setDisable(true);
           setDetails();
     }
     @FXML public void buttonClickEdit(){
        
         engEpfno.setEditable(true);
         engName.setEditable(true);
         engNic.setEditable(true);
         engPrefName.setEditable(true);
         editProfile.setVisible(false);
         resign.setVisible(true);
         done.setVisible(true);
         cancle.setVisible(true);
         slectDeparment.setDisable(false);
        
     }
     
     private void proceedWithEditing() throws RemoteException, NotBoundException{
         
        noFaultAtname = (ValidateEmployee.isnull(engName));
        noFaultAtEpf=checkEpf();
        noFaultAtPref=checkPref();
        noFaultAtNic=checkNic();
        if(noFaultAtEpf&&noFaultAtNic&&noFaultAtPref&&noFaultAtname){
            conformEditing();
        }
        
     }
     
     private void conformEditing() throws RemoteException, NotBoundException{
         String oldEpf = emp.getEpfId();
         String name = this.engName.getText();
        String EpfNo = this.engEpfno.getText();
        String prefName = this.engPrefName.getText();
        String nic = this.engNic.getText();
        emp.setEpfId(EpfNo);
        emp.setNIC(nic+"V");
        emp.setName(name);
        emp.setPreferedName(prefName);
        if (emp instanceof Engineer)
        emp  = EditEngineer((Engineer)emp);
        else if(emp instanceof OIC) emp = EditOIC((OIC) emp);
        else emp = EditWorker((Worker)emp);
        dbc.editprofile(emp, oldEpf);
        buttonCLickedCalnce();
        Dialog.showInfo("Success", "Edited details are saved");
        
     }
     
     private Employee EditEngineer(Engineer emp){
         if(slectDeparment.getSelectionModel().getSelectedIndex()==0)
         emp.setDepartment(new ElectricalDepartment());
         else emp.setDepartment(new MechanicalDepartment());
         return emp;
     }
     
     private Employee EditOIC(OIC emp){
         Factory f= new Factory();
         f.setID(3);
         emp.setFactor(f);
         return emp;
     } 
      private Employee EditWorker(Worker emp){
         if(slectDeparment.getSelectionModel().getSelectedIndex()==0)
         emp.setDepartment(new ElectricalDepartment());
         else emp.setDepartment(new MechanicalDepartment());
         return emp;
     }
    @FXML public void NICKeyRelesed(){
        int length = engNic.getText().length();
        int pos =engNic.getCaretPosition();
        ValidateEmployee.validateNic(engNic);
        if(engNic.getText().length()==length)engNic.positionCaret(pos);
        else if(engNic.getText().length()>pos) engNic.positionCaret(pos-1); else
        engNic.positionCaret(engNic.getText().length());
    }
    @FXML public void EPFKeyRelesed(){
        int length = engEpfno.getText().length();
        int pos =engEpfno.getCaretPosition();
        ValidateEmployee.validateFEpf(engEpfno);
        if(engEpfno.getText().length()==length)engEpfno.positionCaret(pos);
        else if(engEpfno.getText().length()>pos) engEpfno.positionCaret(pos-1);else 
        engEpfno.positionCaret(engEpfno.getText().length());
    }
    @FXML public void NameKeyReleased(){
        int length = engName.getText().length();
        int pos =engName.getCaretPosition();
        ValidateEmployee.validateFname(engName);
        if(engName.getText().length()==length)engName.positionCaret(pos);
        else if(engName.getText().length()>pos) engName.positionCaret(pos-1);else 
        engName.positionCaret(engName.getText().length());
    }
    @FXML public void PrefNameKeyReleased(){
        int length = engPrefName.getText().length();
        int pos =engPrefName.getCaretPosition();
        ValidateEmployee.validateFpref(engPrefName);
        if(engPrefName.getText().length()==length)engPrefName.positionCaret(pos);
        else if(engPrefName.getText().length()>pos) engPrefName.positionCaret(pos-1);else 
        engPrefName.positionCaret(engPrefName.getText().length());
    }
    
    @FXML public void pref(){
        if(!noFaultAtPref){
       if (engPrefName.getText().equals("INVALIED ENTRY")) engPrefName.setText(""); 
        engPrefName.setStyle(null);
        noFaultAtPref=true;
        }
    } 
    @FXML public void nic(){
        if(!noFaultAtNic){
            
        if (engNic.getText().length()>15) engNic.setText(engNic.getText().substring(0,10));
        if (engNic.getText().equals("INVALIED ENTRY")) engNic.setText(""); 
        engNic.setStyle(null);
            noFaultAtNic=true;} 
    }
    @FXML public void epf(){
        if(!noFaultAtEpf){
        if (engEpfno.getText().equals("INVALIED ENTRY")) engEpfno.setText(""); 
        engEpfno.setStyle(null);
        noFaultAtEpf=true;
        }}
    @FXML public void name(){
        if(!noFaultAtname){
        if (engName.getText().equals("INVALIED ENTRY")) engName.setText(""); 
        engName.setStyle(null);
        noFaultAtname=true;
        }}
      
    private boolean checkPref() throws RemoteException, NotBoundException{
        System.out.println("pref is called#$@$#@"+emp.getPreferedName()+" pref VS pref"+engPrefName.getText());
       if(emp.getPreferedName().equals(engPrefName.getText()))return true;
        else return ValidateEmployee.validatePref(engPrefName.getText(), engPrefName);
   }
    private boolean checkEpf() throws RemoteException, NotBoundException{
       if(emp.getEpfId().equals(engEpfno.getText())) return true;
        else return ValidateEmployee.validateEpf(engEpfno.getText(), engEpfno);
   }
    private boolean checkNic() throws RemoteException, NotBoundException{
        System.out.println(emp.getNIC()+" VS "+engNic.getText());
        if(emp.getNIC().equalsIgnoreCase(engNic.getText()+"V")) return true;
        
        else return ValidateEmployee.validateNic(engNic.getText(), engNic);
   }
    
    private boolean authorize(){
        if (emp.getEpfId().equalsIgnoreCase(MainWindowController.loggUser.getEpfId())) return true;
        else if(emp instanceof Engineer) return false;
        else return true;
    }

    
}



