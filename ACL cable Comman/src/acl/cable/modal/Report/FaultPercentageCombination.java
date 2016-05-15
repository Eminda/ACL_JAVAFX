/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.modal.Report;

/**
 *
 * @author Eminda
 */
public class FaultPercentageCombination {
    private int totalErrorHourEE;
    private int totalErrorHourME;
    private double percentageEE;
    private double percentageME;
    private int totalBreakDown;

    public FaultPercentageCombination() {
    }

    public FaultPercentageCombination(int totalErrorHourEE, int totalErrorHourME,  double percentageME, int totalBreakDown) {
        this.totalErrorHourEE = totalErrorHourEE;
        this.totalErrorHourME = totalErrorHourME;
        this.percentageME = percentageME;
        this.totalBreakDown = totalBreakDown;
    }

    /**
     * @return the totalErrorHourEE
     */
    public int getTotalErrorHourEE() {
        return totalErrorHourEE;
    }

    /**
     * @return the totalErrorHourME
     */
    public int getTotalErrorHourME() {
        return totalErrorHourME;
    }

    /**
     * @return the percentageEE
     */
    public double getPercentageEE() {
        return percentageEE;
    }

    /**
     * @return the percentageME
     */
    public double getPercentageME() {
        return percentageME;
    }

    /**
     * @return the totalBreakDown
     */
    public int getTotalBreakDown() {
        return totalBreakDown;
    }
  

    
    
}
