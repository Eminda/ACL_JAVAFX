/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.comman;

import java.util.Calendar;

/**
 *
 * @author Eminda
 */
public class FaultReport {
    private Cost cost;
    private Calendar workerCompletionTime;
    private Calendar engineerCompletionTime;
    private double totalCost;

    public FaultReport() {
    }
    public Calendar getWorkerCompletionTime() {
        return workerCompletionTime;
    }

    public void setWorkerCompletionTime(Calendar workerCompletionTime) {
        this.workerCompletionTime = workerCompletionTime;
    }

    public Calendar getEngineerCompletionTime() {
        return engineerCompletionTime;
    }

    public void setEngineerCompletionTime(Calendar engineerCompletionTime) {
        this.engineerCompletionTime = engineerCompletionTime;
    }
    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    /**
     * @return the totalCost
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
