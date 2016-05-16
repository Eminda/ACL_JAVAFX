/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UeserController;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class test {

    public static void main(String[] args) throws RemoteException, NotBoundException {
       DBController db = new DBController();
       ArrayList<ResignedEmployee> rm = db.getretiredEmployee(1, "", "", "");
       System.out.println("test final");
        for (ResignedEmployee rm1 : rm) {
            System.out.println("test final");
            System.out.println(rm1.getName());
        }
    }
}
