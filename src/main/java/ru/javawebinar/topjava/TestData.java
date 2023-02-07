package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestData {
   public static final List<Meal> meals = Arrays.asList(
            new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );
}
