/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UeserController;

import ACL_serverAccess.ServerAccess;
import Controllers.ValidationController;


import acl.cable.modal.comman.Employee;
import com.sun.corba.se.spi.activation.Server;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class ValidateEmployee {
    private static final ServerAccess access = new ServerAccess();
    private static ValidationController validtr;
    public static boolean validate(Employee emp, TextField fNic , TextField fPref , TextField epf ) throws RemoteException, NotBoundException{
        boolean result1 = validateEpf(emp.getEpfId(), epf);
        
        boolean result2 = validateNic(emp.getNIC(), fNic);
        boolean result3 = validatePref(emp.getPreferedName(), fPref);
        return (result1&& result2&& result3);
        
        
    }
    
    
    
    
    private static boolean checkNIC(String nic){
        System.out.println(nic.length());
        if(nic.length()!=9)return false;
        try{
        int i=Integer.parseInt(nic.substring(0, nic.length()));}
        catch(NumberFormatException e){
        return false;
        }return true;
        
    }
    
    public static void validateNic(TextField nicTxt) {
        String tel = nicTxt.getText();
        tel = tel.replaceAll("[\\D]", "");
        if (tel.length() > 9) {
            tel = tel.substring(0, 9);
        }
        nicTxt.setText(tel);
    }
    
    public static void validateFEpf(TextField telTxt) {
        String tel = telTxt.getText();
        tel = tel.replaceAll("[\\D]", "");
        if (tel.length() > 5) {
            tel = tel.substring(0, 5);
        }
        telTxt.setText(tel);
    }
    
    public static void validateFname(TextField txt) {
        
        String text = txt.getText();
        String newText = text.replaceAll("[^a-zA-Z ]","");;
        if (newText.length() > 150) {
            newText = newText.substring(0, 150);
        }
        txt.setText(newText);
}
    public static void validateFpref(TextField txt){
         
        String text = txt.getText();
        String newText = text.replaceAll("[^a-zA-Z0-9]","");;
        if (newText.length() > 20) {
            newText = newText.substring(0, 20);
        }
        txt.setText(newText);
    }
    
    
    private static void changeLabel(TextField lbl){
        System.out.println("called");
        lbl.setStyle("-fx-text-fill: red");
    }

    public static boolean validateNic(String nic,TextField nicF) throws RemoteException, NotBoundException{
        System.out.println(nic);
        if(!isnull(nicF))return false;
        validtr = access.getValidator();
        boolean result = validtr.isNic(nic+"V");
        boolean result2 = checkNIC(nic);
        if(result==false||result2==false){
            if(!result){
            changeLabel(nicF);
            //Dialog.show("WARNING", "the nic"+nic+"V already exists");
            Dialog.showError("The NIC "+nic+"V already exists");
            }
            changeLabel(nicF);
            return false;
        }return true;
        
    }
    
    public static boolean validateEpf(String epf,TextField epfF) throws RemoteException, NotBoundException{
        System.out.println(epf);
        System.out.println();
        if(!isnull(epfF))return false;
        validtr = access.getValidator();
        boolean result = validtr.isEpf(epf);
        if(result==false){
            changeLabel(epfF);
            Dialog.showError("The EPF number "+epf+" already exists");
            return false;
        }return true;
        
    }
    
    public static boolean validatePref(String pref,TextField prefF) throws RemoteException, NotBoundException{
        System.out.println(pref);
        if(!isnull(prefF))return false;
            
        validtr = access.getValidator();
        boolean result = validtr.isPref(pref);
        if(result==false){
            changeLabel(prefF);
            Dialog.showError("The Prefered Name "+pref+" is not available");
            return false;
        }return true;
        
    }
    
    public static boolean isnull(TextField text){
        if("".equals(text.getText())||"INVALIED ENTRY".equals(text.getText())){changeLabel(text);text.setText("INVALIED ENTRY");return false;}return true;      
    }
}
