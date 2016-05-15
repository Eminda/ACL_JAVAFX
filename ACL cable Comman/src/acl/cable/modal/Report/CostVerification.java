/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Division;
import acl.cable.modal.comman.Machine;
import java.util.Date;

/**
 *
 * @author Eminda
 */
public class CostVerification implements Report {

    private Division division;
    private Machine machine;
    private long from;
    private long to;
    private long totalBreakDown;
    private long electricalBreakDown;
    private long mechanicalBreakDown;
    private double totalCost;
    private double electricalCost;
    private double mechanicalCost;
    

    public CostVerification() {
    }

    public CostVerification(Division division, Machine machine, long from, long to, long totalBreakDown, long electricalBreakDown, long mechanicalBreakDown, double totalCost, double electricalCost, double mechanicalCost) {
        this.division = division;
        this.machine = machine;
        this.from = from;
        this.to = to;
        this.totalBreakDown = totalBreakDown;
        this.electricalBreakDown = electricalBreakDown;
        this.mechanicalBreakDown = mechanicalBreakDown;
        this.totalCost = totalCost;
        this.electricalCost = electricalCost;
        this.mechanicalCost = mechanicalCost;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
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

    public long getTotalBreakDown() {
        return totalBreakDown;
    }

    public void setTotalBreakDown(long totalBreakDown) {
        this.totalBreakDown = totalBreakDown;
    }

    public long getElectricalBreakDown() {
        return electricalBreakDown;
    }

    public void setElectricalBreakDown(long electricalBreakDown) {
        this.electricalBreakDown = electricalBreakDown;
    }

    public long getMechanicalBreakDown() {
        return mechanicalBreakDown;
    }

    public void setMechanicalBreakDown(long mechanicalBreakDown) {
        this.mechanicalBreakDown = mechanicalBreakDown;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getElectricalCost() {
        return electricalCost;
    }

    public void setElectricalCost(double electricalCost) {
        this.electricalCost = electricalCost;
    }

    public double getMechanicalCost() {
        return mechanicalCost;
    }

    public void setMechanicalCost(double mechanicalCost) {
        this.mechanicalCost = mechanicalCost;
    }
    
    
}
