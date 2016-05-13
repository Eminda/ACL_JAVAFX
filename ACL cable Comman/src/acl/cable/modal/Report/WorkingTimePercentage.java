/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

/**
 *
 * @author Eminda
 */
public class WorkingTimePercentage {
    private long workerTime;
    private long departmentTime;
    private int departmentAssings;
    private int workerAssigns;

    public WorkingTimePercentage() {
    }

    public long getWorkerTime() {
        return workerTime;
    }

    public void setWorkerTime(long workerTime) {
        this.workerTime = workerTime;
    }

    public long getDepartmentTime() {
        return departmentTime;
    }

    public void setDepartmentTime(long departmentTime) {
        this.departmentTime = departmentTime;
    }

    public int getDepartmentAssings() {
        return departmentAssings;
    }

    public void setDepartmentAssings(int departmentAssings) {
        this.departmentAssings = departmentAssings;
    }

    public int getWorkerAssigns() {
        return workerAssigns;
    }

    public void setWorkerAssigns(int workerAssigns) {
        this.workerAssigns = workerAssigns;
    }
    
}
