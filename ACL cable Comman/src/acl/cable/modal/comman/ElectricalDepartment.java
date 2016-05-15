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
public class ElectricalDepartment extends Department{

    public ElectricalDepartment() {
        this.setId(1);
    }
    
    
    public ArrayList<Engineer> getEngineersInElectricalDepartment(){
        return electricalEngineers;
    }
}
