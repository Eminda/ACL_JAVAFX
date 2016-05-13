/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.dBAccess;

import acl.cable.modal.Report.CostVerification;
import acl.cable.modal.comman.Machine;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eminda
 */
public class NewClass {
    public static void main(String[] args) {
        CostVerification costVerification=new CostVerification();
        costVerification.setMachine(new Machine(34, "f", null));
        costVerification.setFrom(new Date(1463066229644L));
        costVerification.setTo(new Date(1423066229644L));
        try {
            ReportDBAccess.getCostVerificationReport(costVerification);
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
