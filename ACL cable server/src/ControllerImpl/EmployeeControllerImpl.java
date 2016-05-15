/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerImpl;

import Controllers.EmployeeController;
import DBAccess.EmployeeDBAccess;
import acl.cable.modal.comman.Employee;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class EmployeeControllerImpl extends UnicastRemoteObject implements EmployeeController {

    /**
     *
     */

    EmployeeDBAccess db;
    public EmployeeControllerImpl ()throws RemoteException{
        db = new EmployeeDBAccess();
    }
    @Override
    public String addEmployee(Employee employee)throws RemoteException {
        String newID = null;
        try {
            newID = db.addEmployee(employee);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (newID!=null){
            employee.setEpfId(newID);
            
        }
        return newID;
    }

    @Override
    public boolean updateEmployee(Employee employee) throws RemoteException{
        try {
            System.out.println("asdff");
            return db.updateEmployee(employee);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean updateEmployeePassword(Employee employee) throws RemoteException{
        try {
            return db.updateEmployeePassword(employee);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<ArrayList<String>> getEmployee(int val) throws RemoteException{
        ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
        ResultSet result;
        try {
            result = db.getEmployee(val);
            while(result.next()){
                ArrayList<String> crntraw = new ArrayList<String>();
                crntraw.add(result.getString(1));
                crntraw.add(result.getString(2));
                crntraw.add(result.getString(3));
                crntraw.add(result.getString(4));
                crntraw.add(result.getString(5));
                crntraw.add(result.getString(6));
                rows.add(crntraw);
            }return rows;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }return null;
    }

    @Override
    public ArrayList<ArrayList<String>> getResignedEmployee(int val ,String name , String nic , String epf) throws RemoteException{
        ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
        ResultSet result;
        try {
            result = db.getEmployee(val);
            while(result.next()){
                ArrayList<String> crntraw = new ArrayList<String>();
                crntraw.add(result.getString(1));
                crntraw.add(result.getString(2));
                crntraw.add(result.getString(3));
                crntraw.add(result.getString(4));
                crntraw.add(result.getString(5));
                crntraw.add(result.getString(6));
                rows.add(crntraw);
            }return rows;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }return null;
        }
    
}