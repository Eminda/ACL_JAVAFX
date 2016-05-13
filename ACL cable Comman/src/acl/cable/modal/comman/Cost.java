/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.comman;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author Eminda
 */
public class Cost {

    private ArrayList<Part> parts = new ArrayList<>();
    private Engineer assignedEngineer;
    private double indirectSkilledCost;
    private double directSkilledCost;
    private double indirectUnSkilledCost;
    private double directUnskilledCost;
    private Calendar createdTime;

    public Cost() {
    }

    public void addPart(Part part) {
        this.parts.add(part);
    }

    public void setParts(ArrayList<Part> parts) {
        this.parts = parts;
    }

    public Part getPart(int index) {
        if (index>0 && parts.size() > index) {
            return parts.get(index);
        }
        return null;
    }
    public ArrayList<Part> getAllParts(){
        return parts;
    }
    public Engineer getAssignedEngineer(){
        return assignedEngineer;
    }

    /**
     * @param assignedEngineer the assignedEngineer to set
     */
    public void setAssignedEngineer(Engineer assignedEngineer) {
        this.assignedEngineer = assignedEngineer;
    }

    public ArrayList<Part> getParts() {
        return parts;
    }


    public double getIndirectSkilledCost() {
        return indirectSkilledCost;
    }

    public void setIndirectSkilledCost(double indirectSkilledCost) {
        this.indirectSkilledCost = indirectSkilledCost;
    }

    public double getDirectSkilledCost() {
        return directSkilledCost;
    }

    public void setDirectSkilledCost(double directSkilledCost) {
        this.directSkilledCost = directSkilledCost;
    }

    public double getIndirectUnSkilledCost() {
        return indirectUnSkilledCost;
    }

    public void setIndirectUnSkilledCost(double indirectUnSkilledCost) {
        this.indirectUnSkilledCost = indirectUnSkilledCost;
    }

    public double getDirectUnskilledCost() {
        return directUnskilledCost;
    }

    public void setDirectUnskilledCost(double directUnskilledCost) {
        this.directUnskilledCost = directUnskilledCost;
    }

    public Calendar getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Calendar createdTime) {
        this.createdTime = createdTime;
    }

}
