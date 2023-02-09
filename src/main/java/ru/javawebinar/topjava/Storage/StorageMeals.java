package ru.javawebinar.topjava.Storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface StorageMeals {
    int getCount();

    void save(Meal m);

    void update(Meal m);

    Meal get(int id);

    void delete(int id);

    List<Meal> getAll();

}
