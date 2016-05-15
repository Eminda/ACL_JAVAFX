package ControllerImpl;

import Controllers.EmployeeController;
import DBAccess.EmployeeDBAccess;
import DBConnection.DBConnectionForClient;
import acl.cable.modal.comman.ElectricalDepartment;
import acl.cable.modal.comman.Engineer;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    public DBConnectionForClient dbConnection;
    public EmployeeDBAccess dbAccess;
    EmployeeControllerImpl cont;
    private ValidationControllerImpl vl;
    /**
     *
     */
    
    public static void main(String[] args) throws InterruptedException, SQLException, RemoteException  {
        test t = new test();
        t.dosomthing();
        
        /*Thread t1=new Thread(new Runnable() {

            @Override
            public void run() {
                t.dosomthing("nic29",12);
            }
        });
        Thread.sleep(10);
        Thread t2=new Thread(new Runnable() {

            @Override
            public void run() {
                t.dosomthing("nic30",6);
            }
        });
        Thread t3 =new Thread(new Runnable() {

            @Override
            public void run() {
                t.dosomthing("nic31",7);
            }
        });
        Thread t4 =new Thread(new Runnable() {

            @Override
            public void run() {
                t.dosomthing("nic32",9);
            }
        });
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();*/
        
    }
    public test() throws RemoteException{
     cont = new EmployeeControllerImpl();
     vl = new ValidationControllerImpl();
    }
    public void dosomthing() throws SQLException, RemoteException{
//   Engineer e1 = new Engineer("0","0","uxham",null);;
//        ElectricalDepartment dp = new ElectricalDepartment();
//        dp.setId(1);
//        e1.setDepartment(dp);
//        e1.setNIC("nic1");
//        e1.setEpfId("1012");
//        cont.addEmployee(e1);
//        
        System.out.println(vl.isNic("942610336V"));
}
}
