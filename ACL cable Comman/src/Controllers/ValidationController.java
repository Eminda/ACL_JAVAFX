/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author HP
 */
public interface ValidationController extends Remote {
    public boolean isPref(String pref) throws RemoteException;
    public boolean isNic(String nic)throws RemoteException;
    public boolean isEpf(String Epf)throws RemoteException;
}
