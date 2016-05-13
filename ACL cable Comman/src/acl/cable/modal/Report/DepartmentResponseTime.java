/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Department;
import acl.cable.modal.comman.Fault;
import java.util.ArrayList;

/**
 *
 * @author Eminda
 */
public class DepartmentResponseTime {
    private long averageResponseTime;
    private int responseLessThan10;
    private int responseLessThan20;
    private Department department;
    private long totalTime;

    public DepartmentResponseTime() {
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public long getAverageResponseTime() {
        return averageResponseTime;
    }

    public void setAverageResponseTime(long averageResponseTime) {
        this.averageResponseTime = averageResponseTime;
    }

    public int getResponseLessThan10() {
        return responseLessThan10;
    }

    public void setResponseLessThan10(int responseLessThan10) {
        this.responseLessThan10 = responseLessThan10;
    }

    public int getResponseLessThan20() {
        return responseLessThan20;
    }

    public void setResponseLessThan20(int responseLessThan20) {
        this.responseLessThan20 = responseLessThan20;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
    public long getOtherTime(){
        return totalTime-responseLessThan10-responseLessThan20;
    }
    
    
}
