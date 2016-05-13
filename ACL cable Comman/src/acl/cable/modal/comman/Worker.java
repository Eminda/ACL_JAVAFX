/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.comman;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Eminda
 */
public class Worker extends Employee {
    private Department department;
    private boolean isOnDuty;

    public Worker() {
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public boolean isWorkerOnDuty(){
        return isOnDuty;
    }
    public void setIfWorkerOnDuty(boolean isOnDuty){
        this.isOnDuty=isOnDuty;
    }

    
}
