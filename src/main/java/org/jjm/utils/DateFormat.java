package org.jjm.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateFormat {
    private DateFormat(){
    }

    public static String format(LocalDateTime t){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return t.format(formatter);
    }

    public static String format(LocalDate t){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return t.format(formatter);
    }
}
