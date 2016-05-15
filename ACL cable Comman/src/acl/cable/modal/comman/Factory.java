/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.comman;

import java.util.ArrayList;

/**
 *
 * @author Eminda
 */
public class Factory implements Division{
    private int DIID;
    private String name;
    private String shortName;
    private int numberOfMachines;
    private ArrayList<Machine> machines;

    public Factory() {
    }

    public Factory(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public int getDIID() {
        return DIID;
    }

    public void setDIID(int DIID) {
        this.DIID = DIID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setNumberOfMachines(int numberOfMachines) {
        this.numberOfMachines = numberOfMachines;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }
    
    public void setMachines(ArrayList<Machine> machines){
        this.machines=machines;
    }
    public ArrayList<Machine> getMachines(){
        return machines;
    }

    public int getNumberOfMachines(){
        return machines.size();
    }
    public void addMachine(Machine machine){
        this.machines.add(machine);
    }
}
