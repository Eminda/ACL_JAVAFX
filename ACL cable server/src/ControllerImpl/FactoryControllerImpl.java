/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerImpl;

import Controllers.EmployeeController;
import Controllers.FactoryController;
import Controllers.ValidationController;
import acl.cable.modal.comman.Employee;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class FactoryControllerImpl extends UnicastRemoteObject implements FactoryController {

    public FactoryControllerImpl() throws RemoteException{
        
    }
    @Override
    public EmployeeController getEmployee()throws RemoteException {
        return new EmployeeControllerImpl();
        
    }
    
    @Override
    public ValidationController getValidation() throws RemoteException{
        return new ValidationControllerImpl();
    };
    
}
