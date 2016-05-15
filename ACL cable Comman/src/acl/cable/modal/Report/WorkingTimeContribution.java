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
    private ArrayList<Fault> faults=new ArrayList<>();

    public WorkingTimeContribution() {
    }
    public void addFault(Fault fault){
        faults.add(fault);
    }
    public void setAllFaults(ArrayList<Fault> faults){
        this.faults=faults;
    }
    public ArrayList<Fault> getAllFaults(){
        return faults;
    }
    public Fault getFault(int index){
        if(index>0 && faults.size()>index){
            return faults.get(index);
        }
        return null;
    }
}
