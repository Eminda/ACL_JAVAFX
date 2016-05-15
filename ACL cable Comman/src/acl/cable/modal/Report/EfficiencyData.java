/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Machine;
import acl.cable.modal.comman.Fault;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Eminda
 */
public class EfficiencyData {
    private Machine machine;
    private Time toalWorkingTime;
    private Time totalBreakDownTime;
    private double workingPercentage;
    private ArrayList<Fault> faultReport;

    public EfficiencyData() {
    }

    public EfficiencyData(Machine machine, Time toalWorkingTime, Time totalBreakDownTime, double workingPercentage) {
        this.machine = machine;
        this.toalWorkingTime = toalWorkingTime;
        this.totalBreakDownTime = totalBreakDownTime;
        this.workingPercentage = workingPercentage;
    }

    /**
     * @return the machine
     */
    public Machine getMachine() {
        return machine;
    }

    /**
     * @param machine the machine to set
     */
    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    /**
     * @return the toalWorkingTime
     */
    public Time getToalWorkingTime() {
        return toalWorkingTime;
    }

    /**
     * @param toalWorkingTime the toalWorkingTime to set
     */
    public void setToalWorkingTime(Time toalWorkingTime) {
        this.toalWorkingTime = toalWorkingTime;
    }

    /**
     * @return the totalBreakDownTime
     */
    public Time getTotalBreakDownTime() {
        return totalBreakDownTime;
    }

    /**
     * @param totalBreakDownTime the totalBreakDownTime to set
     */
    public void setTotalBreakDownTime(Time totalBreakDownTime) {
        this.totalBreakDownTime = totalBreakDownTime;
    }

    /**
     * @return the workingPercentage
     */
    public double getPercentage() {
        return workingPercentage;
    }
    
}
