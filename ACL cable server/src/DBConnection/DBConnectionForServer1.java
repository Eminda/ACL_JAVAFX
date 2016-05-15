/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dell™
 */
public class DBConnectionForServer1 {
    private Connection conn;
    private static DBConnectionForServer1 dBConnection;
    private DBConnectionForServer1() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        conn=DriverManager.getConnection("jdbc:mysql://localhost/ES","root","mysql");
    }
    public static DBConnectionForServer1 getInstance() throws ClassNotFoundException, SQLException{
        if(dBConnection==null){
            dBConnection=new DBConnectionForServer1();
        }
        return dBConnection;
    }
    public Connection getConnection(){
        return conn;
    }
}
