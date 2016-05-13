/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.controllerImpl;

import acl.cable.controllers.EmployeeController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Eminda
 */
public class EmployeeControllerImpl extends UnicastRemoteObject implements EmployeeController{
    public EmployeeControllerImpl()throws RemoteException{
        
    }
    
}
