/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import ControllerImpl.FactoryControllerImpl;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author HP
 */
public class Server {

    public static void main(String[] args) {
        try{
            Registry rg = LocateRegistry.createRegistry(1010) ;
            FactoryControllerImpl ip = new FactoryControllerImpl();
            rg.rebind("myFactory", ip);
            System.out.println("server is started.....");
        }catch(Exception ex){
            
        }
    }
}
