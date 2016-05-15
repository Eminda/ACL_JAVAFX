/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.validation;

/**
 *
 * @author Eminda
 */
public class SimpleValidation {

    public static String getSimpleDouble(double value) {
        Double d=new Double(Math.round(value*100)/100.0);
        return d.toString();
    }

}
