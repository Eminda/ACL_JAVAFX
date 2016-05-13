/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acl.cable.dBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dell™
 */
public class DBConnection {
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/ES","root","mysql");
        return conn;
    }
}
