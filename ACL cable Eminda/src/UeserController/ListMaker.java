/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UeserController;

import Controllers.EmployeeController;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author HP
 */
public class ListMaker {
    public static ArrayList<EmployeeTable> employees;
    public EmployeeController ctrl;
    
    
    
    public ArrayList<ResignedEmployee> getRetiredEmployee(int val , String name, String nic , String Epf ) throws RemoteException{
        if (val==0)return getAllResignedEmployee(name, nic, Epf);else{
        ArrayList<ArrayList<String>> list = ctrl.getResignedEmployee(val, name, nic, Epf);
        ArrayList<ResignedEmployee> temp = new ArrayList<ResignedEmployee>();
        for (ArrayList<String> list1 : list) {ResignedEmployee re = new ResignedEmployee(list1.get(0), list1.get(1),list1.get(2),list1.get(3),Long.parseLong(list1.get(4)), list1.get(5));
        temp.add(re);
            System.out.println(re.geResignedDate());
        
        }
        return temp;}
    }
    
    private ArrayList<ResignedEmployee> getAllResignedEmployee(String name, String nic , String Epf ) throws RemoteException{
        ArrayList<ResignedEmployee> temp = new ArrayList<ResignedEmployee>();
        temp.addAll(getRetiredEmployee(1, name, nic, Epf));
        temp.addAll(getRetiredEmployee(2, name, nic, Epf));
        temp.addAll(getRetiredEmployee(3, name, nic, Epf));
        return temp;
    }
    
    
   
    public ArrayList<EmployeeTable> getCurrentEng() throws RemoteException{
        System.out.println("eng called");
        ListMaker.employees = new ArrayList<>();
        ArrayList<ArrayList<String>> list =this.getListCurrentEng();
        for (ArrayList<String> list1 : list) {
            EmployeeTable emp = new EmployeeTable(list1.get(0), list1.get(1), list1.get(2),list1.get(3),list1.get(4),list1.get(5));
            ListMaker.employees.add(emp);
            System.out.println(emp.getPhoto());
        }
        return this.employees;
        
    }
    
     public ArrayList<EmployeeTable> getCurrentOIC() throws RemoteException{
         ListMaker.employees = new ArrayList<>();
        ArrayList<ArrayList<String>> list =this.getListCurrentOIC();
        for (ArrayList<String> list1 : list) {
            EmployeeTable emp = new EmployeeTable(list1.get(0), list1.get(1), list1.get(2),list1.get(3),list1.get(4),list1.get(5));
            ListMaker.employees.add(emp);
            
        }
        return this.employees;
        
    }
    
     public ArrayList<EmployeeTable> getCurrentWkr() throws RemoteException{
         ListMaker.employees = new ArrayList<>();
        ArrayList<ArrayList<String>> list =this.getListCurrentWkr();
        for (ArrayList<String> list1 : list) {
            EmployeeTable emp = new EmployeeTable(list1.get(0), list1.get(1), list1.get(2),list1.get(3),list1.get(4),list1.get(5));
            ListMaker.employees.add(emp);
        }
        return this.employees;
        
    }
     
      public ArrayList<EmployeeTable> getCurrentAll() throws RemoteException{
         ListMaker.employees = new ArrayList<>();
        ArrayList<ArrayList<String>> list =this.getListCurrentAll();
        for (ArrayList<String> list1 : list) {
            EmployeeTable emp = new EmployeeTable(list1.get(0), list1.get(1), list1.get(2),list1.get(3),list1.get(4),list1.get(5));
            ListMaker.employees.add(emp);
        }
        return this.employees;
        
        
      }
      
      public ArrayList<EmployeeTable> filter(int val2){
          ArrayList<EmployeeTable> temp = new ArrayList<EmployeeTable>();
          if (val2==0) return ListMaker.employees;
          else{
              ListMaker.employees.stream().filter((temp1) -> (temp1.getDid() == null ? val2+"" == null : temp1.getDid().equals(val2+""))).forEach((temp1) -> {
                  temp.add(temp1);
              });
              return temp;
          }
      }
    
    
    
    
    public ListMaker(EmployeeController ctrl){
        this.ctrl = ctrl;
        
    }
    public ArrayList<ArrayList<String>> getListCurrentEng() throws RemoteException{
        ArrayList<ArrayList<String>> list=ctrl.getEmployee(1);
        return list;
    }
    public ArrayList<ArrayList<String>> getListCurrentOIC() throws RemoteException{
        ArrayList<ArrayList<String>> list=ctrl.getEmployee(2);
        return list;
    }
    public ArrayList<ArrayList<String>> getListCurrentWkr() throws RemoteException{
        ArrayList<ArrayList<String>> list=ctrl.getEmployee(3);
        return list;
    }
     
   public ArrayList<ArrayList<String>> getListCurrentAll() throws RemoteException{
        ArrayList<ArrayList<String>> list1=getListCurrentEng();
        ArrayList<ArrayList<String>> list2=getListCurrentOIC();
        ArrayList<ArrayList<String>> list3=getListCurrentWkr();
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        return list;}
}
