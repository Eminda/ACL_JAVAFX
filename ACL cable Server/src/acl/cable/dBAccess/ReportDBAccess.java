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
import acl.cable.modal.Report.MachineEffieciencyFaultDetail;
import acl.cable.modal.Report.MonthlyReport;
import acl.cable.modal.Report.MonthlyReportDivision;
import acl.cable.modal.Report.MonthlyReportRecord;
import acl.cable.modal.Report.Report;
import acl.cable.modal.Report.ResponseTime;
import acl.cable.modal.Report.WorkingTime;
import acl.cable.modal.Report.WorkingTimeContribution;
import acl.cable.modal.Report.WorkingTimeFault;
import acl.cable.modal.Report.WorkingTimePercentage;
import acl.cable.modal.comman.Department;
import acl.cable.modal.comman.Division;
import acl.cable.modal.comman.Factory;
import acl.cable.modal.comman.Fault;
import acl.cable.modal.comman.Machine;
import acl.cable.modal.comman.Worker;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
        String sql="select (select sum(fr.JobCompletionTime) from Fault f,FaultReport fr,worker_fault wf where f.FID=fr.FID and f.FID=wf.faultid and wf.workerid='"+workingTime.getWorker().getEpfId()+"' and f.status=0 and f.faultOccur between "+workingTime.getFrom()+" and "+workingTime.getTo()+") as workerTime,(select sum(fr.JobCompletionTime) from Fault f,FaultReport fr where f.FID=fr.FID and f.isElectrical="+b.toString()+" and f.status=0 and f.faultOccur between "+workingTime.getFrom()+" and "+workingTime.getTo()+") as departmentTiime,(select count(f.FID) from Fault f where f.isElectrical="+b.toString()+" and f.status=0 and f.faultOccur between "+workingTime.getFrom()+" and "+workingTime.getTo()+") as departmentAssigns,(select count(f.FID) from Fault f,FaultReport fr,Worker_fault wf where f.FID=fr.FID and f.FID=wf.faultid and wf.workerid='"+workingTime.getWorker().getEpfId()+"' and f.status=0 and f.faultOccur between "+workingTime.getFrom()+" and "+workingTime.getTo()+") as workerAssigned;";
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
        String sql="select (select sum(fr.JobCompletionTime) from Fault f,FaultReport fr,Machine m where f.status=0 and f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and f.isMaintenance=false and (f.faultOccur between "+cost.getFrom()+" and "+cost.getTo()+")) as totalTime,(select sum(fr.JobCompletionTime) from Fault f,FaultReport fr,Machine m where f.status=0 and f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and f.isMaintenance=false and isElectrical=true  and (f.faultOccur between "+cost.getFrom()+" and "+cost.getTo()+")) as electricalTime,(select sum(fr.JobCompletionTime) from Fault f,FaultReport fr,Machine m where f.status=0 and f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and f.isMaintenance=false and isElectrical=false  and (f.faultOccur between "+cost.getFrom()+" and "+cost.getTo()+")) as mechanicalTime,(select sum(c.TotalValue) from Fault f,FaultReport fr,Machine m,Cost c where f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and fr.CID=c.CID and f.status=0  and (f.faultOccur between "+cost.getFrom()+" and "+cost.getTo()+")) as totalCost,(select sum(c.TotalValue) from Fault f,FaultReport fr,Machine m,Cost c where f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and fr.CID=c.CID and f.status=0 and f.isElectrical=true  and (f.faultOccur between "+cost.getFrom()+" and "+cost.getTo()+")) as electicalCost,(select sum(c.TotalValue) from Fault f,FaultReport fr,Machine m,Cost c where f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and fr.CID=c.CID and f.status=0 and f.isElectrical=false  and (f.faultOccur between "+cost.getFrom()+" and "+cost.getTo()+")) as mechanicalCost;";
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
        String sql="select distinct(f.FID),f.genId,di.name,m.name,f.description,Date_format((SELECT FROM_UNIXTIME(f.faultOccur/1000)),'%Y-%m-%d') as Date,Date_format((SELECT FROM_UNIXTIME(f.faultOccur/1000)),'%r') as Time from Fault f,Machine m,Division di,Worker_fault wf where f.FID=wf.faultid and wf.workerid='"+workingTime.getWorker().getEpfId()+"' and f.mid=m.MID and m.DIID=di.DIID and f.status=0 and (f.faultOccur between "+workingTime.getFrom()+" and "+workingTime.getTo()+") "+conditionForSearch+";";
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
            try {
                return rst.getLong(1);
            } catch (NullPointerException nullPointerException) {
                return Calendar.getInstance().getTimeInMillis();
            }
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
        String sql="select "+(to-from)+"as TotalTime,(select sum(fr.JobCompletionTime) from Fault f,FaultReport fr,Machine m where f.status=0 and f.FID=fr.FID and f.mid=m.MID and f.isMaintenance=false and m.MID='"+machine.getId()+"' and (f.faultOccur between "+from+" and "+to+")) as breakDown,(select sum(c.TotalValue) from Fault f,FaultReport fr,Machine m,Cost c where f.FID=fr.FID and f.mid=m.MID and m.MID='"+machine.getId()+"' and fr.CID=c.CID and f.status=0  and (f.faultOccur between "+from+" and "+to+")) as totalCost,"+machine.getName()+";";
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

    public static Report MachineEfficiencyFaults(MachineEfficiency machineEfficiency) throws SQLException, ClassNotFoundException {
        long from=machineEfficiency.getFaults().getFrom();
        long to=machineEfficiency.getFaults().getTo();
        Machine m=machineEfficiency.getMachine();
        String sql="select f.FID,f.genId,f.description,Date_format((SELECT FROM_UNIXTIME(f.faultOccur/1000)),'%Y-%m-%d') as Date,Date_format((SELECT FROM_UNIXTIME(f.faultOccur/1000)),'%r') as Time,isElectrical,f.noOfWorkers,(select c.TotalValue from faultReport fr,Cost c where f.FID=fr.FID and fr.CID=c.CID) as Cost from Fault f,Machine m where f.mid=m.MID and f.status=0 and f.mid='"+m.getId()+"' and (f.faultOccur between "+from+" and "+to+") order by f.genID;";
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        HashMap<String,MachineEffieciencyFaultDetail> map =new LinkedHashMap<>();
        ResultSet rst=stm.executeQuery(sql);
        while(rst.next()){
            String genId=rst.getString(2);
            MachineEffieciencyFaultDetail faultDetail=map.get(genId);
            map.remove(genId);
            if(faultDetail==null){
                faultDetail=new MachineEffieciencyFaultDetail();
                faultDetail.setId(rst.getString(2));
                faultDetail.setJob(rst.getString(3));
                faultDetail.setDate(rst.getString(4));
                faultDetail.setTime(rst.getString(5));
                
            }
            boolean isElectrical=rst.getBoolean("isElectrical");
            if(isElectrical){
                faultDetail.setIsElectrical(true);
                faultDetail.setNoOfElectricalWorkers(rst.getInt("noOfWorkers"));
            }else{
                faultDetail.setIsMechanical(true);
                faultDetail.setNoOfMechanicalWorkers(rst.getInt("noOfWorkers"));
            }
            faultDetail.setCost(rst.getDouble("Cost")+faultDetail.getCost());
            map.put(faultDetail.getId(), faultDetail);
        }
        Collection<MachineEffieciencyFaultDetail> values = map.values();
        machineEfficiency.getFaults().setFaults(new ArrayList<MachineEffieciencyFaultDetail>(values));
        return machineEfficiency;
    }

    public static ArrayList<Factory> getAllDivisions() throws SQLException, ClassNotFoundException {
        String sql="select DIID,name,shortName from Division;";
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<Factory> factorys=new ArrayList<>();
        while(rst.next()){
            Factory f=new Factory();
            f.setDIID(rst.getInt(1));
            f.setName(rst.getString(2));
            f.setShortName(rst.getString(3));
            factorys.add(f);
        }
        rst.close();
        for (int i = 0; i < factorys.size(); i++) {
            Factory factory = factorys.get(i);
            setMachinesToFactory(factory);
        }
        
        return factorys;
    }

    public static void getMonthlyReportOfFactory(Machine m, int year, int month,MonthlyReportDivision monthlyReportDivision) throws SQLException, ClassNotFoundException {
        int feb=28;
        if(year%100==0){
            if(year%400==0){
                feb=29;
            }
        }else{
            if(year%4==0){
                feb=29;
            }
        }
        int []days={31,feb,31,30,31,30,31,31,30,31,30,31};
        String sql="select "+days[month]+"*24 as TotalTime,(select sum(fr.JobCompletionTime) from fault f,FaultReport fr,Machine m where f.FID=fr.FID and f.mid=m.MID and m.MID='"+m.getId()+"'  and f.status=0 and f.isMaintenance=false and Date_format((SELECT FROM_UNIXTIME(f.faultOccur/1000)),'%Y')="+year+" and Date_format((SELECT FROM_UNIXTIME(f.faultOccur/1000)),'%m')="+month+" ) as TotalBreakDown,(select sum(fr.JobCompletionTime) from fault f,FaultReport fr,Machine m where f.FID=fr.FID and f.mid=m.MID and m.MID='"+m.getId()+"' and f.status=0 and f.isElectrical=true and f.isMaintenance=false and Date_format((SELECT FROM_UNIXTIME(f.faultOccur/1000)),'%Y')="+year+" and Date_format((SELECT FROM_UNIXTIME(f.faultOccur/1000)),'%m')="+month+" ) as ElectricalBreakDown, (select sum(fr.JobCompletionTime) from fault f,FaultReport fr,Machine m where f.FID=fr.FID and f.mid=m.MID and m.MID='"+m.getId()+"' and f.status=0 and f.isElectrical=false and f.isMaintenance=false and Date_format((SELECT FROM_UNIXTIME(f.faultOccur/1000)),'%Y')="+year+" and Date_format((SELECT FROM_UNIXTIME(f.faultOccur/1000)),'%m')="+month+" );";
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        
        if(rst.next()){
            MonthlyReportRecord record=new MonthlyReportRecord();
            long totalTime=rst.getLong(1);
            long totalBreakDown=rst.getLong(2);
            long electricalBreakDown=rst.getLong(3);
            long mechanicalBreakDown=rst.getLong(4);
            double converter=1000*60*60;
            record.setTotalTime(totalTime);
            record.setTotalBreakDownTime(totalBreakDown/converter);
            record.setElectricalBreakdownTime(electricalBreakDown/converter);
            record.setMechanicalBreakdownTime(mechanicalBreakDown/converter);
            record.setElectricalBreakDownPrecentage(Math.round((electricalBreakDown/totalTime)*10000)/100.0);
            record.setMechanicalBreakDownPrecentage(Math.round((mechanicalBreakDown/totalTime)*10000)/100.0);
            record.setMachine(m.getName());
            monthlyReportDivision.addRecord(record);
        }
    }

    private static void setMachinesToFactory(Factory factory) throws SQLException, ClassNotFoundException {
        String sql="select m.MID,m.Name from Machine m,Division d where m.DIID=d.DIID and m.DIID='"+factory.getDIID()+"';";
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        while(rst.next()){
            Machine m=new Machine();
            m.setId(rst.getInt(1));
            m.setName(rst.getString(2));
            m.setFactory(factory);
            factory.addMachine(m);
        }
        rst.close();
    }

    public static ArrayList<Worker> getAllWorkers(Department department) throws SQLException, ClassNotFoundException {
        String sql="Select epfNo,name,preferedName  from Worker where DID='"+department.getId()+"' and isresigned =false;";
        ArrayList<Worker> workers=new ArrayList<>();
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(sql);
        while(rst.next()){
            Worker worker=new Worker();
            worker.setEpfId(rst.getString(1));
            worker.setName(rst.getString(2));
            worker.setPreferedName(rst.getString(3));
            worker.setDepartment(department);
            workers.add(worker);
        }
        return workers;
    }
}
