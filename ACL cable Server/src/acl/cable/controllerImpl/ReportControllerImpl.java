/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.controllerImpl;

import acl.cable.controllers.ReportController;
import acl.cable.dBAccess.ReportDBAccess;
import acl.cable.modal.Report.CostVerification;
import acl.cable.modal.Report.DepartmentResponseTime;
import acl.cable.modal.Report.MachineEfficiency;
import acl.cable.modal.Report.MachineEfficiencyFaults;
import acl.cable.modal.Report.MachineEfficiencyPercentage;
import acl.cable.modal.Report.MachineEfficiencyTimeLine;
import acl.cable.modal.Report.MonthlyReport;
import acl.cable.modal.Report.MonthlyReportDivision;
import acl.cable.modal.Report.Report;
import acl.cable.modal.Report.ResponseTime;
import acl.cable.modal.Report.WorkingTime;
import acl.cable.modal.Report.WorkingTimePercentage;
import acl.cable.modal.comman.Department;
import acl.cable.modal.comman.Division;
import acl.cable.modal.comman.ElectricalDepartment;
import acl.cable.modal.comman.Factory;
import acl.cable.modal.comman.Machine;
import acl.cable.modal.comman.MechanicalDepartment;
import acl.cable.modal.comman.Worker;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eminda
 */
public class ReportControllerImpl extends UnicastRemoteObject implements ReportController {

    ReportControllerImpl() throws RemoteException {

    }

    @Override
    public Report getResponseTime() throws RemoteException {
        ResponseTime responseTime = new ResponseTime();
        Department department = null;
        department = new ElectricalDepartment(1, "Electrical");
        try {
            DepartmentResponseTime departmentResponseTime = ReportDBAccess.generateResponseRTimeReportForDepartment(department);
            responseTime.setElectricalDepartmentResponseTime(departmentResponseTime);
            department = new MechanicalDepartment(2, "Mechanical");
            departmentResponseTime = ReportDBAccess.generateResponseRTimeReportForDepartment(department);
            responseTime.setMechanicalDepartmentResponseTime(departmentResponseTime);
            return responseTime;
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        return null;
    }

    @Override
    public Report getWorkingTimeReport(WorkingTime workingTime) throws RemoteException {
        try {
            if(workingTime.getFrom()==0){
                long  oldesTime=ReportDBAccess.getOldesetTime();
                workingTime.setFrom(oldesTime);
            }
            if(workingTime.getTo()==0){
                workingTime.setTo(Calendar.getInstance().getTimeInMillis());
            }
            ReportDBAccess.getWorkinTimePercentageReport(workingTime);
            String conditionForSearch = ReportDBAccess.getSearchingStatementForWorkingTimeContribution(workingTime);
            ReportDBAccess.getWorkingTimeContributionReport(workingTime, conditionForSearch);
            return workingTime;
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        return null;
    }

    @Override
    public Report getCostVerificationReport(CostVerification cost) throws RemoteException {
        try {
            if(cost.getFrom()==0){
                long  oldesTime=ReportDBAccess.getOldesetTime();
                cost.setFrom(oldesTime);
            }
            if(cost.getTo()==0){
                cost.setTo(Calendar.getInstance().getTimeInMillis());
            }
            return ReportDBAccess.getCostVerificationReport(cost);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *Validate view so that future month can't be sent
     * @param monthlyReport
     * @return
     * @throws RemoteException
     */
    @Override
    public Report getMonthlyReport(MonthlyReport monthlyReport) throws RemoteException {
        try {
            ArrayList<Factory> divisions = ReportDBAccess.getAllDivisions();
            for (int i = 0; i < divisions.size(); i++) {
                Factory factory = divisions.get(i);
                MonthlyReportDivision report = new MonthlyReportDivision();
                report.setFactory(factory);
                ArrayList<Machine> machines = factory.getMachines();
                for (int j = 0; j < machines.size(); j++) {
                    Machine get = machines.get(j);
                    ReportDBAccess.getMonthlyReportOfFactory(new Machine(), monthlyReport.getYear(), monthlyReport.getYear(), report);
                }
                monthlyReport.addDivision(report);

            }
            return monthlyReport;
        } catch (SQLException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Report getMachineEfficiencyReport(MachineEfficiency machineEfficiency) throws RemoteException {
        try {
            long oldestTime = ReportDBAccess.getOldesetTime();
            MachineEfficiencyPercentage percentage = new MachineEfficiencyPercentage();
            percentage.setFrom(oldestTime);
            percentage.setTo(Calendar.getInstance().getTimeInMillis());
            machineEfficiency.setPercentage(percentage);
            getMachineEfficiencyPercentage(machineEfficiency);
            setMachineEfficencyTimeLine(machineEfficiency, oldestTime);
            MachineEfficiencyFaults faults = new MachineEfficiencyFaults();
            faults.setFrom(oldestTime);
            faults.setTo(Calendar.getInstance().getTimeInMillis());
            getMachineEfficiencyFaults(machineEfficiency);
            return machineEfficiency;
        } catch (SQLException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void setMachineEfficencyTimeLine(MachineEfficiency machineEfficiency, long oldestTime) throws SQLException, ClassNotFoundException {
        MachineEfficiencyTimeLine timeLine = new MachineEfficiencyTimeLine();
        machineEfficiency.setTimeLine(timeLine);
        int availableMonths = (int) ((oldestTime - Calendar.getInstance().getTimeInMillis()) / (1000 * 60 * 60 * 24 * 30));
        availableMonths = Math.abs(availableMonths);
        HashMap<String, Integer> data = new LinkedHashMap<>();
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if (availableMonths >= 12) {
            availableMonths = 11;
        }
        //We have to show the current month also. That's why -11. If no 12 months. available month will be like (int)4.12=4. Thus do not reduse in that occasion
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.MONTH, -availableMonths);
        for (int i = 0; i < 12; i++) {
            int numberOfFaults = ReportDBAccess.getNumberOfFaultsInMonthAndYear(cal.get(Calendar.MONTH), cal.get(Calendar.YEAR), machineEfficiency.getMachine().getId());
            data.put(monthNames[cal.get(Calendar.MONTH)], numberOfFaults);
        }
        timeLine.setXYData(data);
    }

    @Override
    public Report getMachineEfficiencyPercentage(MachineEfficiency machineEfficiency) throws RemoteException {
        try {
            return ReportDBAccess.MachineEfficiencyPercentage(machineEfficiency);
        } catch (SQLException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Report getMachineEfficiencyFaults(MachineEfficiency machineEfficiency) throws RemoteException {
        try {
            return ReportDBAccess.MachineEfficiencyFaults(machineEfficiency);
        } catch (SQLException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Report getWorkingTimePercentage(WorkingTime workingTime) throws RemoteException {
        try {
            return ReportDBAccess.getWorkinTimePercentageReport(workingTime);
        } catch (SQLException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Report getWorkingTimeContribution(WorkingTime workingTime) throws RemoteException {
        try {
            String conditionForSearch = ReportDBAccess.getSearchingStatementForWorkingTimeContribution(workingTime);
            ReportDBAccess.getWorkingTimeContributionReport(workingTime, conditionForSearch);
            return workingTime;
        } catch (SQLException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Factory> getAllFactorys() throws RemoteException {
        try {
            return ReportDBAccess.getAllDivisions();
        } catch (SQLException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Worker> getAllWorkers(Department department) throws RemoteException {
        try {
            return ReportDBAccess.getAllWorkers(department);
        } catch (SQLException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
