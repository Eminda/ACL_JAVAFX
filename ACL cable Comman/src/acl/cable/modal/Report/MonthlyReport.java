/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Division;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Eminda
 */
public class MonthlyReport implements Report{
    private int year;
    private int month;
    private ArrayList<MonthlyReportDivision> divisions=new ArrayList<>();

    public MonthlyReport() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public ArrayList<MonthlyReportDivision> getDivisions() {
        return divisions;
    }

    public void setDivisions(ArrayList<MonthlyReportDivision> divisions) {
        this.divisions = divisions;
    }
    
    public void addDivision(MonthlyReportDivision d){
        this.divisions.add(d);
    }
    public MonthlyReportDivision getMonthlyReportDivisionFromIndex(int index){
        if(index>0 && index<divisions.size()){
            return divisions.get(index);
        }
        return null;
    }
    
    
}
