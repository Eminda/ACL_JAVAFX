/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable;

import ACL_serverAccess.ServerAccess;
import UeserController.DBController;
import UeserController.Dialog;
import UeserController.ValidateEmployee;
import acl.cable.modal.comman.ElectricalDepartment;
import acl.cable.modal.comman.Engineer;
import acl.cable.modal.comman.MechanicalDepartment;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Eminda
 */
public class EmployeeEngineerDetailController implements Initializable {

    @FXML
    private ToggleGroup deptGroup;
    @FXML
    private Button btnResign;
    @FXML
    private Button btnCreate;
    @FXML private TextField engName;
    @FXML private TextField engEpfno;
    @FXML private TextField engPrefName;
    @FXML private TextField engNic;
    @FXML private RadioButton isElec;
    public boolean noFaultAtEpf=true;
    public boolean noFaultAtNic=true;
    public boolean noFaultAtPref=true;
    public boolean noFaultAtname =true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setButtonPane(boolean removeAll,boolean isCreating,boolean removeResign){
        btnResign.setVisible(false);
        if(removeAll){
            btnCreate.setVisible(false);
        }else{
            if(isCreating){
                btnCreate.setText("Create");
            }else{
                btnCreate.setText("Edit");
                if (!removeResign) {
                    btnResign.setVisible(true);
                }
            }
        }
    }
    @FXML
    public void crtBtnclicked() throws RemoteException, NotBoundException{
        //this.engNic.setStyle(null);
        createEng();
        
    } 
    public void createEng() throws RemoteException, NotBoundException{
        String name = this.engName.getText();
        String EpfNo = this.engEpfno.getText();
        String prefName = this.engPrefName.getText();
        String nic = this.engNic.getText();
        String department;
        Engineer eng = new Engineer("null", EpfNo, name, null);
        eng.setPreferedName(prefName);
        eng.setNIC(nic+"V");
        if(this.isElec.isSelected()) eng.setDepartment(new ElectricalDepartment());
        else eng.setDepartment(new MechanicalDepartment());
        //if(ValidateEmployee.validate(eng,engNic,engPrefName,engEpfno)) 
        noFaultAtname = (ValidateEmployee.isnull(engName));
        noFaultAtEpf=ValidateEmployee.validateEpf(EpfNo, engEpfno);
        
        noFaultAtPref=ValidateEmployee.validatePref(prefName, engPrefName);
        noFaultAtNic=ValidateEmployee.validateNic(nic, engNic);
        if(noFaultAtEpf&&noFaultAtNic&&noFaultAtPref&&noFaultAtname){ new DBController().addEmployee(eng);Dialog.showSuccess(eng);
        clear();
        }
    
    }
    @FXML
    public void NICKeyRelesed(){
        int length = engNic.getText().length();
        int pos =engNic.getCaretPosition();
        ValidateEmployee.validateNic(engNic);
        if(engNic.getText().length()==length)engNic.positionCaret(pos);
        else if(engNic.getText().length()>pos) engNic.positionCaret(pos-1); else
        engNic.positionCaret(engNic.getText().length());
    }
    public void EPFKeyRelesed(){
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
    @FXML
    public void pref(){
        if(!noFaultAtPref){
       if (engPrefName.getText().equals("INVALIED ENTRY")) engPrefName.setText(""); 
        engPrefName.setStyle(null);
        noFaultAtPref=true;
        }
    } 
    @FXML
    public void nic(){
        if(!noFaultAtNic){
            
        if (engNic.getText().length()>15) engNic.setText(engNic.getText().substring(0,10));
        if (engNic.getText().equals("INVALIED ENTRY")) engNic.setText(""); 
        engNic.setStyle(null);
            noFaultAtNic=true;} 
    }
    @FXML
    public void epf(){
        if(!noFaultAtEpf){
        if (engEpfno.getText().equals("INVALIED ENTRY")) engEpfno.setText(""); 
        engEpfno.setStyle(null);
        noFaultAtEpf=true;
        }}
    
    @FXML
    public void name(){
        if(!noFaultAtname){
        if (engName.getText().equals("INVALIED ENTRY")) engName.setText(""); 
        engName.setStyle(null);
        noFaultAtname=true;
        }}
    
    
    
    private void clear(){
        engEpfno.setText("");
        engName.setText("");
        engNic.setText("");
        engPrefName.setText("");
    }
    
    } 
    

