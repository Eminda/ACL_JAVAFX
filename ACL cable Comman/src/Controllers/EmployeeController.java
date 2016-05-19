/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import acl.cable.modal.comman.Employee;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface EmployeeController extends Remote {
    String addEmployee (Employee employee) throws RemoteException;
    boolean updateEmployee(Employee employee,String oldEpf)throws RemoteException;
    boolean updateEmployeePassword(Employee employee)throws RemoteException;
    ArrayList<ArrayList<String>> getEmployee(int val)throws RemoteException;
    ArrayList<ArrayList<String>> getResignedEmployee(int val ,String name , String nic , String epf)throws RemoteException;
    boolean resigneEmployee(String Epf,String date)throws RemoteException;
    boolean rejoinEmployee(String Epf)throws RemoteException;
    boolean resetPassword(Employee emp)throws RemoteException;
    ArrayList<String> checkUser(String userName,String password) throws RemoteException;
}
