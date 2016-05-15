/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acl.cable.Connector;




import acl.cable.controllers.Factory;
import acl.cable.controllers.ReportController;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Dell™
 */
public class ClientConnector {
    private static ClientConnector clientConnector;
    private Factory factory;


    private ClientConnector() throws NotBoundException, MalformedURLException, RemoteException {
        factory=(Factory) Naming.lookup("rmi://localhost:1994/ESGame");
    }
    public static ClientConnector getClientConnector() throws NotBoundException, MalformedURLException, RemoteException{
        if(clientConnector==null){
            clientConnector=new ClientConnector();
        }
        return clientConnector;
    }
    public Factory getFactoryController(){
        return factory;
    }
    public ReportController getReportController() throws RemoteException{
        return factory.getReportController();
    }
}
