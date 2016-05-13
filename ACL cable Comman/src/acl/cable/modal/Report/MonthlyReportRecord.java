/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Machine;

/**
 *
 * @author Eminda
 */
public class MonthlyReportRecord {
    private Machine machine;
    private long totalTime;
    private long electricalBreakdownTime;
    private long mechanicalBreakdownTime;
    private double electricalBreakDownPrecentage;
    private double mechanicalBreakDownPrecentage;

    public MonthlyReportRecord() {
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public long getElectricalBreakdownTime() {
        return electricalBreakdownTime;
    }

    public void setElectricalBreakdownTime(long electricalBreakdownTime) {
        this.electricalBreakdownTime = electricalBreakdownTime;
    }

    public long getMechanicalBreakdownTime() {
        return mechanicalBreakdownTime;
    }

    public void setMechanicalBreakdownTime(long mechanicalBreakdownTime) {
        this.mechanicalBreakdownTime = mechanicalBreakdownTime;
    }

    public double getElectricalBreakDownPrecentage() {
        return electricalBreakDownPrecentage;
    }

    public void setElectricalBreakDownPrecentage(double electricalBreakDownPrecentage) {
        this.electricalBreakDownPrecentage = electricalBreakDownPrecentage;
    }

    public double getMechanicalBreakDownPrecentage() {
        return mechanicalBreakDownPrecentage;
    }

    public void setMechanicalBreakDownPrecentage(double mechanicalBreakDownPrecentage) {
        this.mechanicalBreakDownPrecentage = mechanicalBreakDownPrecentage;
    }
    
}
