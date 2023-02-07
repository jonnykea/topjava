package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Meal {
    private final String uuid;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(String uuid, LocalDateTime dateTime, String description, int calories) {
        this.uuid = uuid;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public String getUuid() {
        return uuid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meal meal = (Meal) o;

        if (calories != meal.calories) return false;
        if (!Objects.equals(uuid, meal.uuid)) return false;
        if (!Objects.equals(dateTime, meal.dateTime)) return false;
        return Objects.equals(description, meal.description);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + calories;
        return result;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

    public int compareTo(Meal m) {
        int result = dateTime.compareTo(m.dateTime);
        return result != 0 ? result : uuid.compareTo(m.uuid);
    }
}
