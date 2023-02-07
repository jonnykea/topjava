package ru.javawebinar.topjava.Storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {

    void clear();

    void save(Meal m);

    void update(Meal m);

    Meal get(String uuid);

    void delete(String uuid);


    List<Meal> getAllSorted();

}
