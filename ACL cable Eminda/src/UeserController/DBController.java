/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UeserController;

import ACL_serverAccess.ServerAccess;
import Controllers.EmployeeController;
import acl.cable.modal.comman.Employee;
import acl.cable.modal.comman.Engineer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class DBController {
    ServerAccess access;
    EmployeeController ctrl;
    ListMaker lm;
    public DBController() throws RemoteException, NotBoundException{
        this.access = new ServerAccess();
        this.ctrl = access.getController();
    }
   
    public boolean addEmployee(Employee emp) throws RemoteException{
        ctrl.addEmployee(emp);
        return true;
    }
    
    public ArrayList<EmployeeTable> getEmployee(int val) throws RemoteException{
        
        lm = new ListMaker(ctrl);
        if(val==1) return lm.getCurrentAll();
        if(val==2) return lm.getCurrentEng();
        if(val==3) return lm.getCurrentOIC();
        if(val==4) return lm.getCurrentWkr();
        return null;}

    public ArrayList<EmployeeTable> getfliteredEmployee(int val2){
        return lm.filter(val2);
    }
    
    public ArrayList<ResignedEmployee> getretiredEmployee(int val , String name, String nic , String Epf) throws RemoteException{
      lm = new ListMaker(ctrl);
       // System.out.println("try 3");
        return lm.getRetiredEmployee(val, name, nic, Epf);
        
    }
    
    public boolean editprofile(Employee emp) throws RemoteException{
        return ctrl.updateEmployee(emp);
    }
}
