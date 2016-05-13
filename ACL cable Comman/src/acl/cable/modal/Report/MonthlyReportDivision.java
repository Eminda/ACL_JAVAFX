/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Factory;
import java.util.ArrayList;

/**
 *
 * @author Eminda
 */
public class MonthlyReportDivision {
    private ArrayList<MonthlyReportRecord> records=new ArrayList<>();
    private Factory factory;

    public MonthlyReportDivision() {
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
    
    public void addRecord(MonthlyReportRecord record){
        records.add(record);
    }
    public MonthlyReportRecord getRecord(int index){
        if(index>0 && records.size()>index){
            return records.get(index);
        }
        return null;
    }
    public ArrayList<MonthlyReportRecord> getAllRecords(){
        return records;
    }
}
