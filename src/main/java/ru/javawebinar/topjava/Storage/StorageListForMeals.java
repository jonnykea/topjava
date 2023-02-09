package ru.javawebinar.topjava.Storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class StorageListForMeals implements StorageMeals {
    private static final Logger LOG = Logger.getLogger(StorageListForMeals.class.getName());

    private final AtomicInteger atomicCounter = new AtomicInteger(1);

    private final List<Meal> listMeals = Collections.synchronizedList(new ArrayList<>());

    public StorageListForMeals() {
        save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public int getCount(){
        return atomicCounter.getAndIncrement();
    }

    @Override
    public void save(Meal m) {
        m.setId(atomicCounter.getAndIncrement());
        listMeals.add(m);
    }

    @Override
    public void update(Meal m) {
        LOG.info("save " + m);
        int searchKey = getSearchKey(m.getId());
        if (searchKey == 0) {
            save(m);
            return;
        }
        listMeals.set(searchKey, m);
    }

    @Override
    public Meal get(int id) {
        LOG.info("get Meal with " + id);
        int searchKey = getSearchKey(id);
        return listMeals.get(searchKey);
    }

    @Override
    public void delete(int id) {
        LOG.info("delete" + id);
        int searchKey = getSearchKey(id);
        listMeals.remove(searchKey);
    }

    @Override
    public List<Meal> getAll() {
        LOG.info("getAllSorted");
        return new ArrayList<>(listMeals);
    }

    private synchronized int getSearchKey(int id) {
        for (int i = 0; i < listMeals.size(); i++) {
            if (listMeals.get(i).getId() == id) {
                return i;
            }
        }
        return 0;
    }
}