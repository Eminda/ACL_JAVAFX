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
    private Date from;
    private Date to;
    private final WorkingTimeContribution contribution=new WorkingTimeContribution();
    private final WorkingTimePercentage percentage=new WorkingTimePercentage();

    public WorkingTime() {
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
    
}
