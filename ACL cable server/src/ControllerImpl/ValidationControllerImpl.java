/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerImpl;

import Controllers.ValidationController;
import DBAccess.EmployeeDBAccess;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ValidationControllerImpl extends UnicastRemoteObject implements ValidationController{
    EmployeeDBAccess db;
    
    public ValidationControllerImpl () throws RemoteException{
        db = new EmployeeDBAccess();
    }
    @Override
    public boolean isPref(String pref) throws RemoteException {
        try {
            return db.isnPref(pref);
        } catch (SQLException ex) {
            Logger.getLogger(ValidationControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ValidationControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }return false;
    }

    @Override
    public boolean isNic(String nic) throws RemoteException {
        try {
            return db.isnNic(nic);
        } catch (SQLException ex) {
            Logger.getLogger(ValidationControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ValidationControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }return false;
    }

    @Override
    public boolean isEpf(String Epf) throws RemoteException {
        try {
            return db.isnEpf(Epf);
        } catch (SQLException ex) {
            Logger.getLogger(ValidationControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ValidationControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }return false;
    }
    
}
