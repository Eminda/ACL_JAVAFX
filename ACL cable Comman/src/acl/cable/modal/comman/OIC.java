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
public class OIC extends Employee{
    private Factory factory;
    private String Password;

    /**
     *
     * @param emplyId
     * @param epfId
     * @param name
     * @param resignedDate
     */
    public OIC(String emplyId, String epfId, String name, Date resignedDate ) {
     super(emplyId, epfId, name, resignedDate);
    }

    public Factory getFactor() {
        return factory;
    }

    public void setFactor(Factory factory) {
        this.factory = factory;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
}
