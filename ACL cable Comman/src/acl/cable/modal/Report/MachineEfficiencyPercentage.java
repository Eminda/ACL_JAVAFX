/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import java.sql.Time;
import java.util.Calendar;

/**
 *
 * @author Eminda
 */
public class MachineEfficiencyPercentage {
    private long from;
    private long to;
    private long totalTime;
    private long breakDownTime;
    private double cost;

    public MachineEfficiencyPercentage() {
    }

    public MachineEfficiencyPercentage(long from, long to, long totalTime, long breakDownTime, double cost) {
        this.from = from;
        this.to = to;
        this.totalTime = totalTime;
        this.breakDownTime = breakDownTime;
        this.cost = cost;
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

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public long getBreakDownTime() {
        return breakDownTime;
    }

    public void setBreakDownTime(long breakDownTime) {
        this.breakDownTime = breakDownTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
}
