/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import acl.cable.modal.comman.Division;
import acl.cable.modal.comman.Machine;

/**
 *
 * @author Eminda
 */
public class MachineEfficiency implements Report{
    private Division division;
    private Machine machine;
    private MachineEfficiencyFaults faults;
    private MachineEfficiencyPercentage percentage;
    

    public MachineEfficiency() {
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
    
    
    
}
