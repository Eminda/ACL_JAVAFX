/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acl.cable.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

/**
 *
 * @author Eminda
 */
public class DatePickerValidations {

    private final static String pattern = "yyyy-MM-dd";

    public static void setDefaultFormat(DatePicker date) {
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter
                    = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        date.setConverter(converter);
        date.setPromptText(pattern.toLowerCase());
    }

    public static String getDateStringFromLocalDate(LocalDate l) {
        DateTimeFormatter dateFormatter
                = DateTimeFormatter.ofPattern(pattern);
        return dateFormatter.format(l);
    }

    public static LocalDate getLocalDateFromLongValue(long value) {
        Date date = new Date(value);
        LocalDate l1 = LocalDate.of(date.getYear(), date.getMonth(), date.getDate());
        return l1;
    }
    public static String getDateStrFromLong(long vale){
        return getDateStringFromLocalDate(getLocalDateFromLongValue(vale));
    }

    public static long getLongDateFromString(String date) {
        SimpleDateFormat f = new SimpleDateFormat(pattern);
        Date d = null;
        try {
            d = f.parse(date);
            long milliseconds = d.getTime();
            return milliseconds;
        } catch (ParseException ex) {
            Logger.getLogger(DatePickerValidations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Calendar.getInstance().getTimeInMillis();
    }
}
