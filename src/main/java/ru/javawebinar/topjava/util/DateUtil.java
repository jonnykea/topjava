package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MMMM/dddd");

    public static LocalDate parse(String date) {
        LocalDate localDate = LocalDate.parse(date, DATE_FORMATTER);
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
    }
}
