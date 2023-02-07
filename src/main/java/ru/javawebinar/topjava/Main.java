package ru.javawebinar.topjava;

import ru.javawebinar.topjava.Storage.ListMeals;
import ru.javawebinar.topjava.Storage.Storage;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo application</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello TopJava Enterprise!" + "\n");

        List<Meal> listMeals = TestData.meals;
        listMeals.forEach(System.out::println);

        System.out.println();

        List<MealTo> mealsTo = MealsUtil.convertToMealsTo(listMeals);
        mealsTo.forEach(System.out::println);

        System.out.println();

        Meal meal = new Meal(UUID.randomUUID().toString(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        System.out.println(meal.getDateTime());

        Storage storage = new ListMeals();
        storage.update(meal);
        System.out.println(storage.get(meal.getUuid()));
    }
}
