/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author HP
 */
public interface FactoryController extends Remote {
    
    public EmployeeController getEmployee()throws RemoteException;
    public ValidationController getValidation() throws RemoteException;
}
