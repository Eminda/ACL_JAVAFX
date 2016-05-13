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
public class Engineer extends Employee {

    private Department department;

    public Engineer() {
    }

    public Engineer(String emplyId, String epfId, String name, Date resignedDate) {
        super(emplyId, epfId, name, resignedDate);
    }

    /**
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }


}
