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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }
    
    public void setMachines(ArrayList<Machine> machines){
        
    }
    public ArrayList<Machine> getMachines(){
        throw new RuntimeException();
    }
    public Machine checkAndGetMachineByName(String name){
        throw new RuntimeException();
    }
    public int getNumberOfMachines(){
        throw new RuntimeException();
    }
}
