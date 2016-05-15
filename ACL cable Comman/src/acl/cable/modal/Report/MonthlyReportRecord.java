/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Machine;
import java.io.Serializable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Eminda
 */
public class MonthlyReportRecord implements Serializable{
    private SimpleIntegerProperty no;
    private SimpleStringProperty machine;
    private SimpleLongProperty totalTime;
    private SimpleDoubleProperty totalBreakDownTime;
    private SimpleDoubleProperty electricalBreakdownTime;
    private SimpleDoubleProperty mechanicalBreakdownTime;
    private SimpleDoubleProperty electricalBreakDownPrecentage;
    private SimpleDoubleProperty mechanicalBreakDownPrecentage;

    public MonthlyReportRecord() {
    }

    public String getMachine() {
        return machine.get();
    }

    public void setMachine(String machine) {
        this.machine.set(machine);
    }

    public long getTotalTime() {
        return totalTime.get();
    }

    public void setTotalTime(long totalTime) {
        this.totalTime.set(totalTime);
    }

    public double getElectricalBreakdownTime() {
        return electricalBreakdownTime.get();
    }
    public double getTotalBreakDownTime(){
        return totalBreakDownTime.get();
    }
    public void setTotalBreakDownTime(double totalBreakDownTime){
        this.totalBreakDownTime.set(totalBreakDownTime);
    }

    public void setElectricalBreakdownTime(double electricalBreakdownTime) {
        this.electricalBreakdownTime.set(electricalBreakdownTime);
    }

    public double getMechanicalBreakdownTime() {
        return mechanicalBreakdownTime.get();
    }

    public void setMechanicalBreakdownTime(double mechanicalBreakdownTime) {
        this.mechanicalBreakdownTime.set(mechanicalBreakdownTime);
    }

    public double getElectricalBreakDownPrecentage() {
        return electricalBreakDownPrecentage.get();
    }

    public void setElectricalBreakDownPrecentage(double electricalBreakDownPrecentage) {
        this.electricalBreakDownPrecentage.set(electricalBreakDownPrecentage);
    }

    public double getMechanicalBreakDownPrecentage() {
        return mechanicalBreakDownPrecentage.get();
    }

    public void setMechanicalBreakDownPrecentage(double mechanicalBreakDownPrecentage) {
        this.mechanicalBreakDownPrecentage.set(mechanicalBreakDownPrecentage);
    }
    public int getNo(){
        return no.get();
    }
    public void setNo(int no){
        this.no.set(no);
    }
}
