package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealCrudRepository;
import ru.javawebinar.topjava.repository.MealMapRepositoryInMemory;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final int LIMIT_CALORIES_BY_DAY = 2000;
    private final MealCrudRepository mealRepository = new MealMapRepositoryInMemory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to users");
        String forward = "";
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("meals", MealsUtil.filteredByStreams(mealRepository.getAll(),
                    LocalTime.MIN, LocalTime.MAX, LIMIT_CALORIES_BY_DAY));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        } else {
            int ID = !action.equals("create") ? Integer.parseInt(request.getParameter("id")) : -1;
            switch (action) {
                case "delete":
                    Meal meal = mealRepository.getByMealId(ID);
                    mealRepository.delete(meal);
                    response.sendRedirect("/meals");
                    break;
                case "get":
                    request.setAttribute("meal", mealRepository.getByMealId(ID));
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);
                    break;
                case "create":
                    request.setAttribute("meal", new Meal(LocalDateTime.now(), "", 0));
                    request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                    break;
                case "update":
                    request.setAttribute("meal", mealRepository.getByMealId(ID));
                    request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int mealId = Integer.parseInt(req.getParameter("mealId"));
        int calories = Integer.parseInt(req.getParameter("calories"));
        String description = req.getParameter("description");
        LocalDateTime ldt = LocalDateTime.parse(req.getParameter("dateTime"), DateTimeFormatter.ISO_DATE_TIME);
        mealRepository.save(mealId, new Meal(ldt, description, calories));
        req.setAttribute("meals", MealsUtil.filteredByStreams(mealRepository.getAll(),
                LocalTime.MIN, LocalTime.MAX, LIMIT_CALORIES_BY_DAY));
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
