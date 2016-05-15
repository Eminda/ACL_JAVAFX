/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Worker;
import java.util.Date;

/**
 *
 * @author Eminda
 */
public class WorkingTime implements Report{
    private Worker worker;
    private long from;
    private long to;
    private  WorkingTimeContribution contribution;
    private  WorkingTimePercentage percentage;

    public WorkingTime() {
    }

    public WorkingTimeContribution getContribution() {
        return contribution;
    }

    public void setContribution(WorkingTimeContribution contribution) {
        this.contribution = contribution;
    }

    public WorkingTimePercentage getPercentage() {
        return percentage;
    }

    public void setPercentage(WorkingTimePercentage percentage) {
        this.percentage = percentage;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
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
    
}
