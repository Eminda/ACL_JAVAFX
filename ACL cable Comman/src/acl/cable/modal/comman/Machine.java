/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.comman;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Eminda
 */
public class Machine implements Serializable{
    private int id;
    private String name;
    private Factory factory;

    public Machine() {
    }

    public Machine(Factory factory,int id, String name, boolean isWorking) {
        this.id = id;
        this.name = name;
        this.factory=factory;
    }
    
    public Machine(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

   

    /**
     * @return the factory
     */
    public Factory getFactory() {
        return factory;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
    
}
