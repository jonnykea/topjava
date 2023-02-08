package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm");

    public static String parse(LocalDate dateTime) {
        return dateTime.format(DATE_FORMATTER);
    }
}
