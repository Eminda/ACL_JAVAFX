/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Eminda
 */
public class MachineEfficiencyTimeLine {
    private  Map<String,Integer> XYData=new LinkedHashMap<> ();

    public MachineEfficiencyTimeLine() {
    }
    
    public void addXY(String x,int y){
        XYData.put(x, y);
    }
    public Map<String,Integer> getXYData(){
        return XYData;
    }

    public void setXYData(Map<String, Integer> XYData) {
        this.XYData = XYData;
    }
    
}
