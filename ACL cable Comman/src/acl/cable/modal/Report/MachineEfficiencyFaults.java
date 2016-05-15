/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Fault;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Eminda
 */
public class MachineEfficiencyFaults implements Serializable{
    private long from;
    private long to;
    private ArrayList<MachineEffieciencyFaultDetail> faults=new ArrayList<>();

    public MachineEfficiencyFaults() {
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public ArrayList<MachineEffieciencyFaultDetail> getFaults() {
        return faults;
    }

    public void setFaults(ArrayList<MachineEffieciencyFaultDetail> faults) {
        this.faults = faults;
    }
    public void addFault(MachineEffieciencyFaultDetail fault){
        faults.add(fault);
    }
    public MachineEffieciencyFaultDetail getFault(int index){
        if(index>0 && faults.size()>index){
            return faults.get(index);
        }
        return null;
    }
}
