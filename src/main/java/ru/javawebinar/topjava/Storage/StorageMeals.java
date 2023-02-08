package ru.javawebinar.topjava.Storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface StorageMeals {

    AtomicInteger size();

    void save(Meal m);

    void update(Meal m);

    Meal get(AtomicInteger id);

    void delete(AtomicInteger id);

    List<Meal> getAllSorted();

}
