package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Storage.ListMeals;
import ru.javawebinar.topjava.Storage.Storage;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private final Storage storage = new ListMeals();

    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uuid = req.getParameter("uuid");
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        Meal m;
        m = new Meal(uuid, dateTime, description, calories);
        storage.update(m);

        log.debug("redirect to meals");
        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            List<Meal> meals = storage.getAllSorted();
            List<MealTo> mealsTo = MealsUtil.convertToMealsTo(meals);
            request.setAttribute("mealsTo", mealsTo);
            request.getRequestDispatcher("/WEB-INF/jsp/listMeals.jsp").forward(request, response);
            return;
        }
        Meal m;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("meals");
                return;
            case "add":
                m = Meal.EMPTY;
                delegate(request, response, m);
                return;
            case "update":
                m = storage.get(uuid);
                delegate(request, response, m);
                return;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
    }

    private void delegate(HttpServletRequest request, HttpServletResponse response, Meal m) throws ServletException, IOException {
        log.debug("Dispatcher");
        request.setAttribute("meal", m);
        request.getRequestDispatcher(
                "/WEB-INF/jsp/edit.jsp"
        ).forward(request, response);
    }
}
