/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Fault;
import java.util.ArrayList;

/**
 *
 * @author Eminda
 */
public class WorkingTimeContribution {
    public static final int ALL=0;
    public static final int FAULTS_ONLY=1;
    public static final int MAINTENANCE_ONLY=2;
    private ArrayList<WorkingTimeFault> faults=new ArrayList<>();
    private int searchType;
    public WorkingTimeContribution() {
    }
    public void addFault(WorkingTimeFault fault){
        faults.add(fault);
    }

    public ArrayList<WorkingTimeFault> getFaults() {
        return faults;
    }

    public void setFaults(ArrayList<WorkingTimeFault> faults) {
        this.faults = faults;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }
    
    public void setAllFaults(ArrayList<WorkingTimeFault> faults){
        this.faults=faults;
    }
    public ArrayList<WorkingTimeFault> getAllFaults(){
        return faults;
    }
    public WorkingTimeFault getFault(int index){
        if(index>0 && faults.size()>index){
            return faults.get(index);
        }
        return null;
    }
}
