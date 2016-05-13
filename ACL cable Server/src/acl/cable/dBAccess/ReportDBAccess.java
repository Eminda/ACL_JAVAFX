/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.dBAccess;

import static acl.cable.dBAccess.ReportDBAccess.MachineEfficiencyPercentage;
import acl.cable.dBConnection.DBConnection;
import acl.cable.modal.Report.CostVerification;
import acl.cable.modal.Report.DepartmentResponseTime;
import acl.cable.modal.Report.MachineEfficiency;
import acl.cable.modal.Report.MachineEfficiencyPercentage;
import acl.cable.modal.Report.MonthlyReport;
import acl.cable.modal.Report.Report;
import acl.cable.modal.Report.ResponseTime;
import acl.cable.modal.Report.WorkingTime;
import acl.cable.modal.Report.WorkingTimeContribution;
import acl.cable.modal.Report.WorkingTimeFault;
import acl.cable.modal.Report.WorkingTimePercentage;
import acl.cable.modal.comman.Department;
import acl.cable.modal.comman.Division;
import acl.cable.modal.comman.Fault;
import acl.cable.modal.comman.Machine;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author Eminda
 */
public class ReportDBAccess {
    public static MachineEfficiency getMachineEfficiencyReport(){
        return null;
    }
    public static DepartmentResponseTime generateResponseRTimeReportForDepartment(Department department) throws SQLException, ClassNotFoundException{
        Boolean b=new Boolean(department.getId()==1);
        String sql="select (select count(f.FID) from Fault f where (f.engassignedtime-f.faultOccur)<=600000 and f.isElectrical="+b.toString()+" and f.status=0) as lessThanTen,(select count(f.FID) from Fault f where (f.engassignedtime-f.faultOccur)<=1200000 and f.isElectrical="+b.toString()+" and f.status=0) as lessThanTwenty,(select count(f.FID) from Fault f where f.isElectrical="+b.toString()+" and f.status=0) as total,(select sum(f.engassignedtime-f.faultOccur)/(count(f.FID)) from Fault f where f.isElectrical="+b.toString()+" and f.status=0) as averageTime ;";
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        if(rst.next()){
            DepartmentResponseTime departmentResponseTime=new DepartmentResponseTime();
            departmentResponseTime.setResponseLessThan10(rst.getInt(1));
            departmentResponseTime.setResponseLessThan20(rst.getInt(2));
            departmentResponseTime.setTotalTime(rst.getInt(3));
            departmentResponseTime.setAverageResponseTime(rst.getLong(4));
            departmentResponseTime.setDepartment(department);
            return departmentResponseTime;
        }
        return null;
    }
    public static WorkingTime getWorkinTimePercentageReport(WorkingTime workingTime) throws SQLException, ClassNotFoundException{
        Department department=workingTime.getWorker().getDepartment();
        Boolean b=new Boolean(department.getId()==1);
        String sql="select (select sum(fr.JobCompletionTime) from Fault f,FaultReport fr,worker_fault wf where f.FID=fr.FID and f.FID=wf.faultid and wf.workerid='"+workingTime.getWorker().getEpfId()+"' and f.status=0 and f.faultOccur between "+workingTime.getFrom().getTime()+" and "+workingTime.getTo().getTime()+") as workerTime,(select sum(fr.JobCompletionTime) from Fault f,FaultReport fr where f.FID=fr.FID and f.isElectrical="+b.toString()+" and f.status=0 and f.faultOccur between "+workingTime.getFrom().getTime()+" and "+workingTime.getTo().getTime()+") as departmentTiime,(select count(f.FID) from Fault f where f.isElectrical="+b.toString()+" and f.status=0 and f.faultOccur between "+workingTime.getFrom().getTime()+" and "+workingTime.getTo().getTime()+") as departmentAssigns,(select count(f.FID) from Fault f,FaultReport fr,Worker_fault wf where f.FID=fr.FID and f.FID=wf.faultid and wf.workerid='"+workingTime.getWorker().getEpfId()+"' and f.status=0 and f.faultOccur between "+workingTime.getFrom().getTime()+" and "+workingTime.getTo().getTime()+") as workerAssigned;";
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        if(rst.next()){
            WorkingTimePercentage percentage=new WorkingTimePercentage();
            percentage.setWorkerTime(rst.getLong(1));
            percentage.setDepartmentTime(rst.getLong(2));
            percentage.setDepartmentAssings(rst.getInt(3));
            percentage.setWorkerAssigns(rst.getInt(4));
            workingTime.setPercentage(percentage);
        }
        return workingTime;
    }
    
    public static CostVerification getCostVerificationReport(CostVerification cost) throws SQLException, ClassNotFoundException{
        Division div=cost.getDivision();
        Machine machine=cost.getMachine();
        String sql="select (select sum(fr.JobCompletionTime) from Fault f,FaultReport fr,Machine m where f.status=0 and f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and f.isMaintenance=false and (f.faultOccur between "+cost.getFrom().getTime()+" and "+cost.getTo().getTime()+")) as totalTime,(select sum(fr.JobCompletionTime) from Fault f,FaultReport fr,Machine m where f.status=0 and f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and f.isMaintenance=false and isElectrical=true  and (f.faultOccur between "+cost.getFrom().getTime()+" and "+cost.getTo().getTime()+")) as electricalTime,(select sum(fr.JobCompletionTime) from Fault f,FaultReport fr,Machine m where f.status=0 and f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and f.isMaintenance=false and isElectrical=false  and (f.faultOccur between "+cost.getFrom().getTime()+" and "+cost.getTo().getTime()+")) as mechanicalTime,(select sum(c.TotalValue) from Fault f,FaultReport fr,Machine m,Cost c where f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and fr.CID=c.CID and f.status=0  and (f.faultOccur between "+cost.getFrom().getTime()+" and "+cost.getTo().getTime()+")) as totalCost,(select sum(c.TotalValue) from Fault f,FaultReport fr,Machine m,Cost c where f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and fr.CID=c.CID and f.status=0 and f.isElectrical=true  and (f.faultOccur between "+cost.getFrom().getTime()+" and "+cost.getTo().getTime()+")) as electicalCost,(select sum(c.TotalValue) from Fault f,FaultReport fr,Machine m,Cost c where f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and fr.CID=c.CID and f.status=0 and f.isElectrical=false  and (f.faultOccur between "+cost.getFrom().getTime()+" and "+cost.getTo().getTime()+")) as mechanicalCost;";
        System.out.println(sql);
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        if(rst.next()){
            cost.setTotalBreakDown(rst.getLong(1));
            cost.setElectricalBreakDown(rst.getLong(2));
            cost.setMechanicalBreakDown(rst.getLong(3));
            cost.setTotalCost(rst.getDouble(4));
            cost.setElectricalCost(rst.getDouble(5));
            cost.setMechanicalCost(rst.getDouble(6));
        }
        return cost;
    }
    public static MonthlyReport getMonthlyReport(){
        return null;
    }

    public static String getSearchingStatementForWorkingTimeContribution(WorkingTime workingTime) {
        int statues=0;
        if(workingTime.getContribution()!=null){
            statues=workingTime.getContribution().getSearchType();
        }
        switch(statues){
            case 0: return "";
            case 1: return "and isMaintenance=false";
            case 2: return "and isMaintenance=true";
        }
        return null;
    }

    public static WorkingTime getWorkingTimeContributionReport(WorkingTime workingTime, String conditionForSearch) throws SQLException, ClassNotFoundException {
        String sql="select distinct(f.FID),f.genId,di.name,m.name,f.description,Date_format((SELECT FROM_UNIXTIME(f.faultOccur/1000)),'%Y-%m-%d') as Date,Date_format((SELECT FROM_UNIXTIME(f.faultOccur/1000)),'%r') as Time from Fault f,Machine m,Division di,Worker_fault wf where f.FID=wf.faultid and wf.workerid='"+workingTime.getWorker().getEpfId()+"' and f.mid=m.MID and m.DIID=di.DIID and f.status=0 and (f.faultOccur between "+workingTime.getFrom().getTime()+" and "+workingTime.getTo().getTime()+") "+conditionForSearch+";";
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        WorkingTimeContribution contribution=new WorkingTimeContribution();
        while(rst.next()){
            WorkingTimeFault fault=new WorkingTimeFault();
            fault.setId(rst.getString(2));
            fault.setFactory(rst.getString(3));
            fault.setMachine(rst.getString(4));
            fault.setJob(rst.getString(5));
            fault.setDate(rst.getString(6));
            fault.setTime(rst.getString(7));
            contribution.addFault(fault);
        }
        workingTime.setContribution(contribution);
        return workingTime;
    }

    public static long getOldesetTime() throws SQLException, ClassNotFoundException {
        String sql="select f.faultOccur from Fault f  order by 1 desc limit 1;";
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        if(rst.next()){
            return rst.getLong(1);
        }
        return Calendar.getInstance().getTimeInMillis();
    }

    public static int getNumberOfFaultsInMonthAndYear(int month, int year,int MID) throws SQLException, ClassNotFoundException {
        month++;
        String sql="select count(f.FID) from Fault f,Machine m where f.mid=m.MID and m.MID='"+MID+"' and status=0 and Date_format((SELECT FROM_UNIXTIME(f.faultOccur)),'%m')="+month+" and Date_format((SELECT FROM_UNIXTIME(f.faultOccur)),'%Y')="+year+";";
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        if(rst.next()){
            return rst.getInt(1);
        }
        return 0;
    }

    public static Report MachineEfficiencyPercentage(MachineEfficiency machineEfficiency) throws SQLException, ClassNotFoundException {
        Machine machine=machineEfficiency.getMachine();
        long from=machineEfficiency.getPercentage().getFrom();
        long to=machineEfficiency.getPercentage().getTo();
        String sql="select "+(to-from)+"as TotalTime,(select sum(fr.JobCompletionTime) from Fault f,FaultReport fr,Machine m where f.status=0 and f.FID=fr.FID and f.mid=m.MID and f.isMaintenance=false and m.MID='"+machine.getId()+"' and (f.faultOccur between "+from+" and "+to+")) as breakDown,(select sum(c.TotalValue) from Fault f,FaultReport fr,Machine m,Cost c where f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and fr.CID=c.CID and f.status=0  and (f.faultOccur between "+from+" and "+to+")) as totalCost;";
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        MachineEfficiencyPercentage percentage=machineEfficiency.getPercentage();
        if(rst.next()){
            percentage.setTotalTime(rst.getLong(1));
            percentage.setBreakDownTime(rst.getLong(2));
            percentage.setCost(rst.getDouble(3));
        }
        return machineEfficiency;
    }
}
