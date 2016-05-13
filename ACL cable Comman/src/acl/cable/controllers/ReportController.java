/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.controllers;

import acl.cable.modal.Report.CostVerification;
import acl.cable.modal.Report.MachineEfficiency;
import acl.cable.modal.Report.MonthlyReport;
import acl.cable.modal.Report.Report;
import acl.cable.modal.Report.WorkingTime;
import acl.cable.modal.comman.Division;
import acl.cable.modal.comman.Machine;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Eminda
 */
public interface ReportController extends Remote{
    Report getMachineEfficiencyReport(MachineEfficiency machineEfficiency)throws RemoteException;
    Report getMachineEfficiencyPercentage(MachineEfficiency machineEfficiency)throws RemoteException;
    Report getMachineEfficiencyFaults(MachineEfficiency machineEfficiency)throws RemoteException;
    Report getResponseTime()throws RemoteException;
    Report getWorkingTimeReport(WorkingTime workingTime)throws RemoteException;
    Report getWorkingTimePercentage(WorkingTime workingTime)throws RemoteException;
    Report getWorkingTimeContribution(WorkingTime workingTime)throws RemoteException;
    Report getCostVerificationReport(CostVerification costVerification)throws RemoteException;
    Report getMonthlyReport(MonthlyReport monthlyReport)throws RemoteException;
}
