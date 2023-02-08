package ru.javawebinar.topjava.Storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ListMeals implements StorageMeals {
    private static final Logger LOG = Logger.getLogger(ListMeals.class.getName());

    private final AtomicInteger atomicCounter = new AtomicInteger(0);

    private final List<Meal> listMeals = new ArrayList<>();

    public ListMeals() {
        save(new Meal(size(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        save(new Meal(size(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        save(new Meal(size(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        save(new Meal(size(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        save(new Meal(size(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        save(new Meal(size(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public AtomicInteger size() {
        return atomicCounter;
    }

    @Override
    public void save(Meal m) {
        listMeals.add(m);
        atomicCounter.getAndIncrement();
    }

    @Override
    public void update(Meal m) {
        LOG.info("save " + m);
        int searchKey = getSearchKey(m.getId());
        if (searchKey == -1) {
            save(m);
            return;
        }
        listMeals.set(searchKey, m);
    }

    @Override
    public Meal get(AtomicInteger id) {
        LOG.info("get Meal with " + id);
        int searchKey = getSearchKey(id);
        return listMeals.get(searchKey);
    }

    @Override
    public void delete(AtomicInteger id) {
        LOG.info("delete" + id);
        int searchKey = getSearchKey(id);
        listMeals.remove(searchKey);
    }

    @Override
    public List<Meal> getAllSorted() {
        LOG.info("getAllSorted");
/*                List<Meal> list = new ArrayList<>(listMeals);
        Collections.sort(list);*/
        return new ArrayList<>(listMeals);
    }

    protected Integer getSearchKey(AtomicInteger id) {
        for (int i = 0; i < listMeals.size(); i++) {
            if (listMeals.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}