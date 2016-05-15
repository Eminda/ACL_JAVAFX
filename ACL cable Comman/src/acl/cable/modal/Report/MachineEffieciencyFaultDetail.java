/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import java.io.Serializable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Eminda
 */
public class MachineEffieciencyFaultDetail implements Serializable{
    private final SimpleStringProperty id=new SimpleStringProperty("");
    private final SimpleStringProperty job=new SimpleStringProperty("");
    private final SimpleStringProperty date=new SimpleStringProperty("");
    private final SimpleStringProperty time=new SimpleStringProperty("");
    private final SimpleBooleanProperty isElectrical=new SimpleBooleanProperty(false);
    private final SimpleBooleanProperty isMechanical=new SimpleBooleanProperty(false);
    private final SimpleIntegerProperty noOfElectricalWorkers=new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty noOfMechanicalWorkers=new SimpleIntegerProperty(0);
    private final SimpleDoubleProperty cost=new SimpleDoubleProperty(0.0);

    public MachineEffieciencyFaultDetail() {
    }
    public void setId(String id){
        this.id.set(id);
    }
    public String getId(){
        return id.get();
    }
    public void setJob(String job){
        this.job.set(job);
    }
    public String getJob(){
        return this.job.get();
    }
    public void setDate(String date){
        this.date.set(date);
    }
    public String getDate(){
        return this.date.get();
    }
    public void setTime(String time){
        this.time.set(time);
    }
    public String getTime(){
        return time.get();
    }
    public boolean isIsElectrical(){
        return isElectrical.get();
    }
    public void setIsElectrical(boolean isElectrical){
        this.isElectrical.set(isElectrical);
    }
    public boolean isIsMechanical(){
        return this.isMechanical.get();
    }
    public void setIsMechanical(boolean isMechanical){
        this.isMechanical.set(isMechanical);
    }
    public void setNoOfElectricalWorkers(int noOfElectricalWorkers){
        this.noOfElectricalWorkers.set(noOfElectricalWorkers);
    }
    public int getNoOfElectricalWorkers(){
        return noOfElectricalWorkers.get();
    }
    public int getNoOfMechanicalWorkers(){
        return noOfMechanicalWorkers.get();
    }
    public void setNoOfMechanicalWorkers(int noOfMechanicalWorkers){
        this.noOfMechanicalWorkers.set(noOfMechanicalWorkers);
    }
    public void setCost(double cost){
        this.cost.set(cost);
    }
    public double getCost(){
        return cost.get();
    }
}
