/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import DBConnection.DBConnectionForClient;
import acl.cable.modal.comman.Employee;
import acl.cable.modal.comman.Engineer;
import acl.cable.modal.comman.OIC;
import acl.cable.modal.comman.Worker;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author HP
 */
public class EmployeeDBAccess {
     private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();  
    public String addEmployee(Employee employee) throws SQLException, ClassNotFoundException{
    lock.writeLock().lock();
        try {
            String sql = this.createSQL(employee);
            Connection conn = DBConnectionForClient.getConnection();
            Statement stm = conn.createStatement();
            int executeUpdate = stm.executeUpdate(sql);
            String sqlget = "select epfnoEng from engineer where nic= '"+employee.getNIC()+"';";
            ResultSet epf = stm.executeQuery(sqlget);
            if (executeUpdate > 0) {
                epf.next();
                return epf.getString("epfnoEng");
            } else {
                return null;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public boolean updateEmployee(Employee employee,String oldEpf ) throws ClassNotFoundException, SQLException {
        lock.writeLock().lock();
        try {
            System.out.println("qqqqqqqqq");
            String sql = updateSQL(employee,oldEpf);
            Connection conn = DBConnectionForClient.getConnection();
            Statement stm = conn.createStatement();
            int executeUpdate = stm.executeUpdate(sql);
            if (executeUpdate > 0) {
                return true;
            } else {
                return false;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public ResultSet checkUIser(String userName,String password) throws SQLException, ClassNotFoundException{
        String Sql1 = "select * from Engineer where preferedname = '"+userName+"' and password = (select password('"+password+"'));";
        String Sql2 = "select * from OIC where preferedname = '"+userName+"' and password = (select password('"+password+"'));";
         lock.writeLock().lock();
        try{
        Connection conn = DBConnectionForClient.getConnection();
        Statement stm = conn.createStatement();
            System.out.println(Sql1);
        ResultSet rs1 = stm.executeQuery(Sql1);
        boolean re1 =rs1.next();
        if (re1){
            return rs1;
            
        }
        ResultSet rs2 = stm.executeQuery(Sql2);
        boolean re2 =rs2.next();
        if (re2){
            return rs2;
        }
        
        }
        finally {lock.writeLock().lock();} 
       return null; 
    }    
    public boolean updateEmployeePassword(Employee employee) throws ClassNotFoundException, SQLException {
//        boolean isOk = false;
//        String table = null;
//       String id;
//       String password = null;
//       if (employee instanceof Engineer){
//           table ="Engineer";
//           id = "epfnoEng";
//           Engineer eng = (Engineer) employee;
//           password = eng.getPassword();
//       }else{
//           table ="OIC";
//           id = "epfnoOIC";
//           OIC eng = (OIC) employee;
//           password = eng.getPassword();
//       
        Engineer eng = (Engineer) employee;
        try {
            lock.writeLock().lock();
            String sql1 = "update Engineer set password = (select password('"+eng.getPassword()+"')) where epfnoEng="+eng.getEpfId()+";";
            String sql2 = "update OIC set password = (select password('"+eng.getPassword()+"')) where epfnoOIC="+eng.getEpfId()+";";
            System.out.println(sql1);
            System.out.println(sql2);
            Connection conn = DBConnectionForClient.getConnection();
            Statement stm = conn.createStatement();
            int rst1 = stm.executeUpdate(sql1);
            int rst2 = stm.executeUpdate(sql2);
            if((rst1>0)||(rst2>0)) return true;
            
        } finally {
            lock.writeLock().unlock();
        }return false;
    }
    
    private boolean setPassword(Employee employee) throws ClassNotFoundException, SQLException {
       String table = null;
       String id;
       String password = null;
       if (employee instanceof Engineer){
           table ="Engineer";
           id = "epfnoEng";
           Engineer eng = (Engineer) employee;
           password = eng.getPassword();
       }else{
           table ="OIC";
           id = "epfnoOIC";
           OIC eng = (OIC) employee;
           password = eng.getPassword();
       }
               
       String sql = "update "+table+" set Password='(select password('" + password + "'))' where "+id+"='" + employee.getEpfId() + "' ;";
        Connection conn = DBConnectionForClient.getConnection();
        Statement stm = conn.createStatement();
       int executeUpdate = stm.executeUpdate(sql);
        if (executeUpdate > 0) {
            return true;
        } else {
            return false;
        }
    
        
            
        }
   
    public boolean isnNic(String nic) throws SQLException, ClassNotFoundException{
        String sql1 = "select name from engineer where nic='"+nic+"';";
        String sql2 = "select name from OIC where nic='"+nic+"';";
        String sql3 = "select name from Worker where nic='"+nic+"';";
        Connection conn = DBConnectionForClient.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs1 = stm.executeQuery(sql1);
        boolean re1 =rs1.next();
        ResultSet rs2 = stm.executeQuery(sql2);
        boolean re2 =rs2.next();
        ResultSet rs3 = stm.executeQuery(sql3);
       boolean re3 =rs3.next();
        
        if (re1==false && re2==false && re3==false)return true;
        return false;
    }
    
    public boolean isnPref(String pref) throws SQLException, ClassNotFoundException{
        String sql1 = "select name from engineer where preferedName='"+pref+"';";
        String sql2 = "select name from OIC where preferedName='"+pref+"';";
        String sql3 = "select name from Worker where preferedName='"+pref+"';";
        Connection conn = DBConnectionForClient.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs1 = stm.executeQuery(sql1);
        boolean re1 =rs1.next();
        ResultSet rs2 = stm.executeQuery(sql2);
        boolean re2 =rs2.next();
        ResultSet rs3 = stm.executeQuery(sql3);
       boolean re3 =rs3.next();
        
        if (re1==false && re2==false && re3==false)return true;
        return false;
    }
    
    
    public boolean isnEpf(String epfno) throws SQLException, ClassNotFoundException{
        String sql1 = "select name from engineer where epfnoEng="+epfno+";";
        String sql2 = "select name from OIC where epfnoOIC="+epfno+";";
        String sql3 = "select name from Worker where epfnoWkr="+epfno+";";
        Connection conn = DBConnectionForClient.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs1 = stm.executeQuery(sql1);
        boolean re1 =rs1.next();
        ResultSet rs2 = stm.executeQuery(sql2);
        boolean re2 =rs2.next();
        ResultSet rs3 = stm.executeQuery(sql3);
       boolean re3 =rs3.next();
        
        if (re1==false && re2==false && re3==false)return true;
        return false;
    }
    
    
    public ResultSet getEmployee(int val) throws SQLException, ClassNotFoundException{
        String sql;
        if (val==1){
            sql = "select name,preferedname,nic,epfnoEng,photo,did from engineer where isresigned=0";
        }else if (val==2){
            sql = "select name,preferedname,nic,epfnoOIC,photo,diid from OIC where isresigned=0";
        }else if (val==3){
            sql = "select name,preferedname,nic,epfnowkr,photo,did from worker where isresigned=0";
        }else return null;
        Connection conn = DBConnectionForClient.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rslt = stm.executeQuery(sql);
        
        return rslt;
    }
    public ResultSet getResignedEmployee(int val,String name,String nic , String epf) throws SQLException, ClassNotFoundException{
        String sql;
        if (val==1){
            //System.out.println("sql");
            sql = "select name,preferedname,nic,epfnoEng,resignedate,did from engineer where name like '"+name+"%' and nic like '"+nic+"%' and epfnoEng like '"+epf+"%' and isresigned=1";
        }else if (val==2){
            sql = "select name,preferedname,nic,epfnooic,resignedate,diid from oic where name like '"+name+"%' and nic like '"+nic+"%' and epfnoOIC like '"+epf+"%' and isresigned!=0";
        }else if (val==3){
            sql = "select name,preferedname,nic,epfnowkr,resignedate,did from worker where name like '"+name+"%' and nic like '"+nic+"%' and epfnoWkr like '"+epf+"%' and isresigned!=0";
        }else return null;
        Connection conn = DBConnectionForClient.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rslt = stm.executeQuery(sql);
        
        return rslt;
    }
    public String createSQL(Employee emp){
            int wrkplaceID;
            String sql = null;
            if (emp instanceof Engineer){
                Engineer eng = (Engineer) emp;
                wrkplaceID = eng.getDepartment().getId();
                sql = "insert into Engineer (DID,nic,name,preferedName,photo,password,isresigned,resignedate,epfnoEng) values ("+wrkplaceID+" , '"+emp.getNIC()+"' , '"+emp.getName()+"' , '"+emp.getPreferedName()+"' , "+null+" , (select password ('ACL@123')) , "+0+" , "+eng.getResignedDate()+" , "+emp.getEpfId()+");";
                System.out.println(sql);
            }else if(emp instanceof OIC){
                OIC oic = (OIC) emp;
                wrkplaceID = oic.getFactor().getID();
                sql = "insert into OIC (diid,nic,name,preferedName,photo,password,isresigned,resignedate,epfnoOIC) values ("+wrkplaceID+" , '"+emp.getNIC()+"' , '"+emp.getName()+"' , '"+emp.getPreferedName()+"' , "+null+" , (select password ('ACL@123')) , "+0+" , "+oic.getResignedDate()+" , "+emp.getEpfId()+");";
            }else if (emp instanceof Worker){
                Worker wrkr = (Worker)emp;
                wrkplaceID = wrkr.getDepartment().getId();
                sql = "insert into worker(DID,nic,name,preferedName,photo,isresigned,resignedate,epfnoWkr) values ("+wrkplaceID+" , '"+emp.getNIC()+"' , '"+emp.getName()+"' , '"+emp.getPreferedName()+"' , "+null+" , "+0+" , "+wrkr.getResignedDate()+" , "+emp.getEpfId()+");";
                }
            return sql;  
        }
    
    public String updateSQL(Employee emp,String oldEpf){
            int wrkplaceID;
            String sql = null;
            if (emp instanceof Engineer){
                Engineer eng = (Engineer) emp;
                wrkplaceID = eng.getDepartment().getId();
                sql = "update Engineer set did="+wrkplaceID+" , name='"+emp.getName()+"' , preferedname='"+emp.getPreferedName()+"' , "+"NIC= '"+emp.getNIC()+"', epfnoEng="+emp.getEpfId()+" where epfnoEng="+oldEpf+";";
                System.out.println(sql);
                }else if(emp instanceof OIC){
                OIC oic = (OIC) emp;
                wrkplaceID = oic.getFactor().getID();
                sql = "update OIC set diid="+wrkplaceID+" , name='"+emp.getName()+"' , preferedname='"+emp.getPreferedName()+"' , "+"NIC= '"+emp.getNIC()+"', epfnoOIC="+emp.getEpfId()+" where epfnoOIC="+oldEpf+";";}
                
                else if (emp instanceof Worker){
                Worker wrkr = (Worker)emp;
                wrkplaceID = wrkr.getDepartment().getId();
                sql = "update Worker set did="+wrkplaceID+" , name='"+emp.getName()+"' , preferedname='"+emp.getPreferedName()+"' , "+"NIC= '"+emp.getNIC()+"', epfnoWkr="+emp.getEpfId()+" where epfnoWkr="+oldEpf+";";}
            System.out.println(sql);
            return sql;  
        }
    
    
    public boolean resignEmployee(String epf,String Date) throws SQLException, ClassNotFoundException{
        lock.writeLock().lock();
        try {
            System.out.println("qqqqqqqqq");
            String sql = resignSQL(epf, Date, 1);
            String sq2 = resignSQL(epf, Date, 2);
            String sq3 = resignSQL(epf, Date, 3);
            Connection conn = DBConnectionForClient.getConnection();
            
            Statement stm = conn.createStatement();
            int executeUpdate1 = stm.executeUpdate(sql);
            System.out.println(sql);
            int executeUpdate2 = stm.executeUpdate(sq2);
            System.out.println(sq2);
            int executeUpdate3 = stm.executeUpdate(sq3);
            System.out.println(sq2);
            if ((executeUpdate1 > 0)||(executeUpdate2 > 0)||(executeUpdate3 > 0)) {
                return true;
            } else {
                return false;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
    
     public boolean rejoinEmployee(String epf) throws SQLException, ClassNotFoundException{
        lock.writeLock().lock();
        try {
            System.out.println("qqqqqqqqq");
            String sql = rejoinEmployeesql(epf,1);
            String sq2 = rejoinEmployeesql(epf,2);
            String sq3 = rejoinEmployeesql(epf,3);
            Connection conn = DBConnectionForClient.getConnection();
            
            Statement stm = conn.createStatement();
            int executeUpdate1 = stm.executeUpdate(sql);
            int executeUpdate2 = stm.executeUpdate(sq2);
            int executeUpdate3 = stm.executeUpdate(sq3);
            if ((executeUpdate1 > 0)||(executeUpdate2 > 0)||(executeUpdate3 > 0)) {
                return true;
            } else {
                return false;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
    private String resignSQL(String epf,String date ,int val){
        if (val==1) return "update Engineer set isresigned=1 , resignedate="+date+" where epfnoEng="+epf+";";
        else if (val==2)return "update OIC set isresigned=1 , resignedate="+date+" where epfnoOIC="+epf+";";
        else return "update Worker set isresigned=1 , resignedate="+date+" where epfnoWkr="+epf+";";
        
    } 
    
    
    private String rejoinEmployeesql(String epf,int val){
        if (val==1) return "update Engineer set isresigned=0 where epfnoEng = "+epf+";";
        else if (val==2) return "update OIC set isresigned=0 where epfnoOIC = "+epf+";";
        else  return "update Worker set isresigned=0 where epfnoWkr = "+epf+";";
    }
}

