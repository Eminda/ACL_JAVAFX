/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.comman;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Eminda
 */
public class Fault {
    public static final int LOW_PRIORITY=0;
    public static final int MED_PRIORITY=1;
    public static final int HIGH_PRIORITY=2;
    public static final int CURRENT=0;
    public static final int EDITED=1;
    public static final int DELETED=2;
    
    
    private String id;
    private String genId;
    private Calendar faultOccur;
    private Calendar engAssignTime;
    
    private long totalTime;
    private int priority;
    private int statues;
    
    private Engineer workerAssiignedEngineer;
    private Machine machine;
    private String description;
    private Department department;
    private FakeFaultReport fakeFaultReport;
    private ArrayList<Worker> assignedElectricalWorkers=new ArrayList<>();
    private ArrayList<Worker> assignedMechanicalWorkers=new ArrayList<>();
    private OIC oic;
    /**
     * Though company have 4 factories and divisions containing machines we have put all machine containing divisions
     * under factory for the ease of maintaining code
     */
    private Factory factory;
    

    private FaultReport faultReport;
    
    

    public Fault() {
    }
    public void addElectricalWorker(Worker worker){
        assignedElectricalWorkers.add(worker);
    }
    public void setAssignedElectricalWorkers(ArrayList<Worker> workers){
        this.assignedElectricalWorkers=workers;
    }
    public Worker getElectricalWorker(int index){
        if(index>0 && assignedElectricalWorkers.size()>index){
            return assignedElectricalWorkers.get(index);
        }
        return null;
    }
    public ArrayList<Worker> getAllElectricalWorkers(){
        return assignedElectricalWorkers;
    }
    public void addMechanicalWorker(Worker worker){
        assignedMechanicalWorkers.add(worker);
    }
    public void setAssignedMechanicalWorkers(ArrayList<Worker> workers){
        this.assignedMechanicalWorkers=workers;
    }
    public Worker getMechanicalWorker(int index){
        if(index>0 && assignedMechanicalWorkers.size()>index){
            return assignedMechanicalWorkers.get(index);
        }
        return null;
    }
    public ArrayList<Worker> getAllMechanicalWorkers(){
        return assignedMechanicalWorkers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenId() {
        return genId;
    }

    public void setGenId(String genId) {
        this.genId = genId;
    }

    public Calendar getFaultOccur() {
        return faultOccur;
    }

    public void setFaultOccur(Calendar faultOccur) {
        this.faultOccur = faultOccur;
    }

    public Calendar getEngAssignTime() {
        return engAssignTime;
    }

    public void setEngAssignTime(Calendar engAssignTime) {
        this.engAssignTime = engAssignTime;
    }

    

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatues() {
        return statues;
    }

    public void setStatues(int statues) {
        this.statues = statues;
    }

    

    public Engineer getEngAssignedEngineer() {
        return workerAssiignedEngineer;
    }

    public void setEngAssignedEngineer(Engineer engAssignedEngineer) {
        this.workerAssiignedEngineer = engAssignedEngineer;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    
    public int getMonthThatFaultReportHappened(){
        if(faultOccur!=null){
            return faultOccur.get(Calendar.MONTH);
        }
        return -1;
    }

    /**
     * @return the faultReport
     */
    public FaultReport getFaultReport() {
        return faultReport;
    }

    /**
     * @param faultReport the faultReport to set
     */
    public void setFaultReport(FaultReport faultReport) {
        this.faultReport = faultReport;
    }
    
    public boolean isMaintenance(){
        
        return false;
    }

    /**
     * @return the factory
     */
    public Factory getFactory() {
        return factory;
    }

    /**
     * @param factory the factory to set
     */
    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    /**
     * @return the oic
     */
    public OIC getOic() {
        return oic;
    }

    /**
     * @param oic the oic to set
     */
    public void setOic(OIC oic) {
        this.oic = oic;
    }

    /**
     * @return the fakeFaultReport
     */
    public FakeFaultReport getFakeFaultReport() {
        return fakeFaultReport;
    }

    /**
     * @param fakeFaultReport the fakeFaultReport to set
     */
    public void setFakeFaultReport(FakeFaultReport fakeFaultReport) {
        this.fakeFaultReport = fakeFaultReport;
    }
    
    public int getTotalNumberOfWorkers(){
        return assignedElectricalWorkers.size()+assignedMechanicalWorkers.size();
    }
    public int getTotalNumberOfElectricalWorker(){
        return assignedElectricalWorkers.size();
    }
    public int getTotalNumberOfMechanicalWorker(){
        return assignedMechanicalWorkers.size();
    }
    
    
}
