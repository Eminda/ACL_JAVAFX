/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import acl.cable.modal.comman.Department;
import acl.cable.modal.comman.Engineer;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class cclientTest {
    public static void main(String[] args) throws RemoteException, NotBoundException, SQLException {
        Registry rg = LocateRegistry.getRegistry("LocalHost",1010);
        FactoryController ft = (FactoryController)rg.lookup("myFactory");
        EmployeeController ct = (EmployeeController)ft.getEmployee();
        ArrayList<ArrayList<String>> list=ct.getEmployee(1);
        for (ArrayList<String> list1 : list) {
            for (String list11 : list1) {
                System.out.print(list11+" ");
            }
            System.out.println();
        }
    }
}
