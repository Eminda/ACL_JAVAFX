/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Fault;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Eminda
 */
public class MachineEfficiencyFaults {
    private Calendar from;
    private Calendar to;
    private ArrayList<Fault> faults=new ArrayList<>();

    public MachineEfficiencyFaults() {
    }

    public Calendar getFrom() {
        return from;
    }

    public void setFrom(Calendar from) {
        this.from = from;
    }

    public Calendar getTo() {
        return to;
    }

    public void setTo(Calendar to) {
        this.to = to;
    }

    public ArrayList<Fault> getFaults() {
        return faults;
    }

    public void setFaults(ArrayList<Fault> faults) {
        this.faults = faults;
    }
    public void addFault(Fault fault){
        faults.add(fault);
    }
    public Fault getFault(int index){
        if(index>0 && faults.size()>index){
            return faults.get(index);
        }
        return null;
    }
}
