/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.comman;

import java.util.Date;

/**
 *
 * @author Eminda
 */
public abstract class Employee {

    /**
     * Employees can be uniquely identified by epfNo. But in the program we need
     * to maintain the exact flow of employees for user friendlyness And the
     * emplyId is used to identify whether the employee is an administrator,
     * engineer or a worker. Hence there are two unique fields for an employee
     */
    private String emplyId;
    private String epfNo;
    private String NIC;
    private String name;
    private String preferedName;
    private Date resignedDate;

    public Employee() {
    }

    public Employee(String emplyId, String epfNo, String name, Date resignedDate) {
        this.emplyId = emplyId;
        this.epfNo = epfNo;
        this.name = name;
        
 
        this.resignedDate = resignedDate;
    }

    /**
     * @return the emplyId
     */
    public String getEmplyId() {
        return emplyId;
    }

    /**
     * @return the epfNo
     */
    public String getEpfId() {
        return epfNo;
    }

    /**
     * @param epfNo the epfNo to set
     */
    public void setEpfId(String epfNo) {
        this.epfNo = epfNo;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return the resignedDate
     */
    public Date getResignedDate() {
        return resignedDate;
    }
    
    public boolean isResigned(){
        return resignedDate!=null;
    }

    /**
     * @return the isWorking
     */
    public boolean isWorking() {
        return resignedDate==null;
    }

    /**
     * @return the preferedName
     */
    public String getPreferedName() {
        return preferedName;
    }

    /**
     * @param preferedName the preferedName to set
     */
    public void setPreferedName(String preferedName) {
        this.preferedName = preferedName;
    }

    /**
     * @return the NIC
     */
    public String getNIC() {
        return NIC;
    }

    /**
     * @param NIC the NIC to set
     */
    public void setNIC(String NIC) {
        this.NIC = NIC;
    }
}
