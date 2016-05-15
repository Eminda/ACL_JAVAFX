/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.comman;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Eminda
 */
public class Company implements Serializable{
    private ArrayList<Employee> employees=new ArrayList<>();
    private ArrayList<Division> divisions=new ArrayList<>();

    public Company() {
    }
    public void addEmployee(Employee employee){
        this.employees.add(employee);
    }
    public void setAllEmployees(ArrayList<Employee> employees){
        this.employees=employees;
    }
    public ArrayList<Employee> getAllEmployees(){
        throw new RuntimeException();
    }
    public Employee getEmployee(int indexOfList){
        throw new RuntimeException();
    }
    public void addDivision(Division div){
        this.divisions.add(div);
    }
    public void setAllFactories(ArrayList<Division> divisions){
        this.divisions=divisions;
    }
    public ArrayList<Factory> getAllFactories(){
        throw new RuntimeException();
    }
    public Factory getFactory(int indexOfList){
        throw new RuntimeException();
    }
}
