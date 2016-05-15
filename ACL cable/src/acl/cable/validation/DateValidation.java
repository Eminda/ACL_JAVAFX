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
public class DateValidation {
    public static String getTimeFormated(long time){
        int days=(int) (time/(1000*60*60*24));
        int remainder=(int) (time%(1000*60*60*24));
        int hours=remainder/(1000*60*60);
        remainder=remainder%(1000*60*60);
        int minutes=remainder/(1000*60);
        remainder=remainder%(1000*60);
        int secods=remainder%1000;
        String timeStr="";
        if(days!=0){
            timeStr+=days+" Days ";
        }
        if(hours!=0){
            timeStr+=hours+" H ";
        }
        if(minutes!=0){
            timeStr+=minutes+" Min ";
        }
        return timeStr;
    }
}
