package ru.javawebinar.topjava.Storage;

import ru.javawebinar.topjava.TestData;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ListMeals implements Storage {
    private static final Logger LOG = Logger.getLogger(ListMeals.class.getName());
    private final List<Meal> listMeals = TestData.meals;

    public ListMeals() {
        new ArrayList<>(listMeals);
    }

    @Override
    public void clear() {
        listMeals.clear();
    }

    @Override
    public void save(Meal m) {
        listMeals.add(m);
    }

    @Override
    public void update(Meal m) {
        LOG.info("save " + m);
        int searchKey = getSearchKey(m.getUuid());
        if (searchKey == 0) {
            save(m);
            return;
        }
        listMeals.set(searchKey, m);
    }

    @Override
    public Meal get(String uuid) {
        int searchKey = getSearchKey(uuid);
        return listMeals.get(searchKey);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("delete" + uuid);
        int searchKey = getSearchKey(uuid);
        listMeals.remove(searchKey);

    }

    @Override
    public List<Meal> getAllSorted() {
        LOG.info("getAllSorted");
               /* List<Meal> list = new ArrayList<>(listMeals);
        Collections.sort(list);*/
        return new ArrayList<>(listMeals);
    }

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < listMeals.size(); i++) {
            if (listMeals.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return 0;
    }
}
