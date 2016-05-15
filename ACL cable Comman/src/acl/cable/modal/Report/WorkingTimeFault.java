/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Eminda
 */
public class WorkingTimeFault implements Serializable{
    private final SimpleStringProperty id=new SimpleStringProperty("");
    private final SimpleStringProperty machine=new SimpleStringProperty("");
    private final SimpleStringProperty factory=new SimpleStringProperty("");
    private final SimpleStringProperty job=new SimpleStringProperty("");
    private final SimpleStringProperty date=new SimpleStringProperty("");
    private final SimpleStringProperty time=new SimpleStringProperty("");

    public WorkingTimeFault() {
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getMachine() {
        return machine.get();
    }

    public void setMachine(String machine) {
        this.machine.set(machine);
    }

    public String getFactory() {
        return factory.get();
    }

    public void setFactory(String factory) {
        this.factory.set(factory);
    }

    public String getJob() {
        return job.get();
    }

    public void setJob(String job) {
        this.job.set(job);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time.set(time);
    }
    
}
