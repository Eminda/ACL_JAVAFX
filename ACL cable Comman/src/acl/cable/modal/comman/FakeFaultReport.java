/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.comman;

import acl.cable.modal.Report.Report;
import java.util.Calendar;

/**
 *
 * @author Eminda
 */
public class FakeFaultReport implements Report{
    private Fault faultReport;
    private Engineer falseReportCreatedEng;
    private Calendar time;
    private String description;
    

    public FakeFaultReport() {
    }

    public FakeFaultReport(Fault faultReport, Engineer falseReportCreatedEng, Calendar time) {
        this.faultReport = faultReport;
        this.falseReportCreatedEng = falseReportCreatedEng;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the faultReport
     */
    public Fault getFaultReport() {
        return faultReport;
    }

    /**
     * @param faultReport the faultReport to set
     */
    public void setFaultReport(Fault faultReport) {
        this.faultReport = faultReport;
    }

    /**
     * @return the falseReportCreatedEng
     */
    public Engineer getFalseReportCreatedEng() {
        return falseReportCreatedEng;
    }

    /**
     * @param falseReportCreatedEng the falseReportCreatedEng to set
     */
    public void setFalseReportCreatedEng(Engineer falseReportCreatedEng) {
        this.falseReportCreatedEng = falseReportCreatedEng;
    }

    /**
     * @return the time
     */
    public Calendar getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Calendar time) {
        this.time = time;
    }

    
    
}
