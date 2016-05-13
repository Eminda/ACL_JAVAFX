/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.controllers;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Eminda
 */
public interface Factory extends Remote{
    EmployeeController getEmployeeController()throws RemoteException;
    MachineController getMachineController()throws RemoteException;
    FaultController getFaultController()throws RemoteException;
    MailController getMailController()throws RemoteException;
    ReportController getReportController()throws RemoteException;
}
