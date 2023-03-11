package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class MealMapRepositoryInMemory implements MealCrudRepository {

    private final Map<Integer, Meal> mealMap = new HashMap<>();

    public MealMapRepositoryInMemory() {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
        meals.forEach(m -> save(m.getId(), m));
    }

    @Override
    public Meal save(int mealId, Meal meal) {
        meal.setId(mealId);
        if (mealMap.containsKey(mealId)) {
            return mealMap.computeIfPresent(mealId, (integer, meal1) -> meal);
        }
        return mealMap.put(mealId, meal);
    }

    @Override
    public boolean delete(Meal meal) {
        return mealMap.remove(meal.getId()) != null;
    }

    @Override
    public Meal getByMealId(Integer mealId) {
        return mealMap.get(mealId);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealMap.values());
    }
}
