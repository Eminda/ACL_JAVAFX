/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the edidestinationEmployeer.
 */
package acl.cable.modal.comman;

import acl.cable.modal.Report.Report;

/**
 *
 * @author Eminda
 */
public class Mail {
    private Report attachedReport;
    private String body;
    private String subject;
    private Division from;
    private Division to;

    public Mail() {
    }

    public Mail(Report attachedReport, String body, String subject, Division from, Division to) {
        this.attachedReport = attachedReport;
        this.body = body;
        this.subject = subject;
        this.from = from;
        this.to = to;
    }

   public Mail(String body, String subject, Division from, Division to) {
       
        this.body = body;
        this.subject = subject;
        this.from = from;
        this.to = to;
    }

    public Report getAttachedReport() {
        return attachedReport;
    }

    public void setAttachedReport(Report attachedReport) {
        this.attachedReport = attachedReport;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Division getFrom() {
        return from;
    }

    public void setFrom(Division from) {
        this.from = from;
    }

    public Division getTo() {
        return to;
    }

    public void setTo(Division to) {
        this.to = to;
    }

    /**
     * @return the report
     */
    public Report getReport() {
        return attachedReport;
    }

    /**
     * @param report the report to set
     */
    public void setReport(Report report) {
        this.attachedReport = report;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return body;
    }

    /**
     * @param message the message to set
     */
    public void setMsg(String message) {
        this.body = message;
    }

    
    
}
