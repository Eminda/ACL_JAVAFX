/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.controllerImpl;

import acl.cable.controllers.EmployeeController;
import acl.cable.controllers.Factory;
import acl.cable.controllers.FaultController;
import acl.cable.controllers.MachineController;
import acl.cable.controllers.MailController;
import acl.cable.controllers.ReportController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Eminda
 */
public class ConcreteFactory extends UnicastRemoteObject implements Factory{
    
    public ConcreteFactory()throws RemoteException{
        
    }
    
    @Override
    public EmployeeController getEmployeeController() throws RemoteException {
        return new EmployeeControllerImpl();
    }

    @Override
    public MachineController getMachineController() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FaultController getFaultController() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MailController getMailController() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReportController getReportController() throws RemoteException {
        return new ReportControllerImpl();
    }
    
}
