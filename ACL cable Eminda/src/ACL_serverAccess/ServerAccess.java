/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ACL_serverAccess;



import Controllers.EmployeeController;
import Controllers.FactoryController;
import Controllers.ValidationController;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ServerAccess {
    
    public EmployeeController getController() throws RemoteException, NotBoundException{
        Registry rg = LocateRegistry.getRegistry("LocalHost",1010);
        FactoryController ft = (FactoryController)rg.lookup("myFactory");
        EmployeeController ct = (EmployeeController)ft.getEmployee();
        return ct;
    }
    
    public ValidationController getValidator() throws RemoteException, NotBoundException{
        Registry rg = LocateRegistry.getRegistry("LocalHost",1010);
        FactoryController ft = (FactoryController)rg.lookup("myFactory");
        ValidationController vc = (ValidationController)ft.getValidation();
        return vc;
    }
}
