/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Department;
import java.util.ArrayList;

/**
 *
 * @author Eminda
 */
public class ResponseTime implements Report{
    private DepartmentResponseTime electricalDepartmentResponseTime;
    private DepartmentResponseTime mechanicalDepartmentResponseTime;

    public ResponseTime() {
    }

    public DepartmentResponseTime getElectricalDepartmentResponseTime() {
        return electricalDepartmentResponseTime;
    }

    public void setElectricalDepartmentResponseTime(DepartmentResponseTime electricalDepartmentResponseTime) {
        this.electricalDepartmentResponseTime = electricalDepartmentResponseTime;
    }

    public DepartmentResponseTime getMechanicalDepartmentResponseTime() {
        return mechanicalDepartmentResponseTime;
    }

    public void setMechanicalDepartmentResponseTime(DepartmentResponseTime mechanicalDepartmentResponseTime) {
        this.mechanicalDepartmentResponseTime = mechanicalDepartmentResponseTime;
    }
    
    
}
