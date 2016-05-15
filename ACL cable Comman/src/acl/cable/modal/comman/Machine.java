/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.comman;

import java.util.Calendar;

/**
 *
 * @author Eminda
 */
public class Machine {
    private String id;
    private String name;
    private Calendar addedDate;
    private Calendar removedDate;
    private boolean isWorking;
    private Factory factory;

    public Machine() {
    }

    public Machine(Factory factory,String id, String name, Calendar addedDate, Calendar removedDate, boolean isWorking) {
        this.id = id;
        this.name = name;
        this.addedDate = addedDate;
        this.removedDate = removedDate;
        this.isWorking = isWorking;
        this.factory=factory;
    }
    
    public Machine(String id, String name, Calendar addedDate) {
        this.id = id;
        this.name = name;
        this.addedDate = addedDate;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the addedDate
     */
    public Calendar getAddedDate() {
        return addedDate;
    }

    /**
     * @return the removedDate
     */
    public Calendar getRemovedDate() {
        return removedDate;
    }

    /**
     * @param removedDate the removedDate to set
     */
    public void setRemovedDate(Calendar removedDate) {
        this.removedDate = removedDate;
    }

    /**
     * @return the isWorking
     */
    public boolean isWorking() {
        return isWorking;
    }

    /**
     * @return the factory
     */
    public Factory getFactory() {
        return factory;
    }
}
