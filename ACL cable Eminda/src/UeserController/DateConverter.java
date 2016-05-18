/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UeserController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author HP
 */
public class DateConverter {

    public static String getLDate(){
        long millis = System.currentTimeMillis()  ;
        String st = millis+"";
        return st;
    }
    
    public static String getSDate(String lDate){
        Date dt = new Date(Long.parseLong(lDate)); 
       String sdt = new SimpleDateFormat("yyyy-MM-dd").format(dt);
       return sdt;
    }
    
}
