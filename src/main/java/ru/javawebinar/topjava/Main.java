package ru.javawebinar.topjava;

import ru.javawebinar.topjava.Storage.ListMeals;
import ru.javawebinar.topjava.Storage.StorageMeals;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo application</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello TopJava Enterprise!" + "\n");
        System.out.println();

        StorageMeals storageMeals = new ListMeals();
        Meal meal = new Meal(storageMeals.size(), LocalDateTime.now(), "Завтрак", 500);
        storageMeals.update(meal);
        System.out.println(storageMeals.size());
    }
}