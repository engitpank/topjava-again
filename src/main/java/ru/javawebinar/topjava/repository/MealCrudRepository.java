package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealCrudRepository {
    Meal save(int mealId, Meal meal);

    boolean delete(Meal meal);

    Meal getByMealId(Integer mealId);

    List<Meal> getAll();
}
