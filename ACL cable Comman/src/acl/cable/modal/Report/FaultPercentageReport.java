/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Machine;
import java.util.HashMap;

/**
 *
 * @author Eminda
 */
public class FaultPercentageReport implements Report{
    private int totalHours;
    private HashMap<Machine,FaultPercentageCombination> dataSheet;

    public FaultPercentageReport() {
    }
    public void callculateReportForTheMonth(int year,int month){
        
    }
    public FaultPercentageCombination getFaultCombinationOfMachine(Machine machine){
        throw new RuntimeException();
    }

    /**
     * @return the totalHours
     */
    public int getTotalHours() {
        return totalHours;
    }

    /**
     * @param totalHours the totalHours to set
     */
    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    /**
     * @return the dataSheet
     */
    public HashMap<Machine,FaultPercentageCombination> getDataSheet() {
        return dataSheet;
    }

    /**
     * @param dataSheet the dataSheet to set
     */
    public void setDataSheet(HashMap<Machine,FaultPercentageCombination> dataSheet) {
        this.dataSheet = dataSheet;
    }
    public FaultPercentageCombination getResultOfMachine(Machine machine){
        throw new RuntimeException();
    }
    public void addFaultCombinationOfMachine(FaultPercentageCombination faultPercentageCombination,Machine machine){
        dataSheet.put(machine, faultPercentageCombination);
    }
}
