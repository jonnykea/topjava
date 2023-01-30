package ru.javawebinar.topjava.util;

import java.time.LocalTime;

public class DateUtil {
    public static Boolean isTimeBetween(LocalTime comparingTime, LocalTime startTime, LocalTime endTime) {
        return comparingTime.compareTo(startTime) >= 0 && comparingTime.compareTo(endTime) < 0;
    }
}
