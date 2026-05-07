package com.cinema.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter simpleFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(formatter);
    }

    public static String getCurrentDate() {
        return LocalDateTime.now().format(simpleFormatter);
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
}
