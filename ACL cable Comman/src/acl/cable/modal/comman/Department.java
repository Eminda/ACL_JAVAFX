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
public abstract class Department implements Division{
    private int id;
    private String name;
    protected ArrayList<Engineer> electricalEngineers=new ArrayList<>();
    protected ArrayList<Engineer> mechanicalEngineers=new ArrayList<>();
    protected ArrayList<Worker> workers=new ArrayList<>();
    
    public void addElectricalEngineer(Engineer eng){
        electricalEngineers.add(eng);
    }
    public void setAllElectricalEngineers(ArrayList<Engineer> engineers){
        this.electricalEngineers=engineers;
    }
    public ArrayList<Engineer> getAllElectricalEngineers(){
        return this.electricalEngineers;
    }
    public Engineer getElectricalEngineer(int index){
        if(electricalEngineers.size()>index){
            return electricalEngineers.get(index);
        }else{
            return null;
        }
    }
    public void addMechanicalEngineer(Engineer eng){
        mechanicalEngineers.add(eng);
    }
    public void setAllMechanicalEngineers(ArrayList<Engineer> engineers){
        this.mechanicalEngineers=engineers;
    }
    public ArrayList<Engineer> getAllMechanicalEngineers(){
        return this.mechanicalEngineers;
    }
    public Engineer getMechanicalEngineer(int index){
        if(mechanicalEngineers.size()>index){
            return mechanicalEngineers.get(index);
        }else{
            return null;
        }
    }
    public void addWorker(Worker eng){
        workers.add(eng);
    }
    public void setAllWorkers(ArrayList<Worker> workers){
        this.workers=workers;
    }
    public ArrayList<Worker> getAllWorkers(){
        return this.workers;
    }
    public Worker getWorker(int index){
        if(workers.size()>index){
            return workers.get(index);
        }else{
            return null;
        }
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
