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
public class MechanicalDepartment extends Department{

    public MechanicalDepartment() {
    }

    public MechanicalDepartment(int id, String name) {
        super(id, name);
    }
    
    public ArrayList<Engineer> getEngineersInMechanicalDepartment(){
        return mechanicalEngineers;
    }
}
