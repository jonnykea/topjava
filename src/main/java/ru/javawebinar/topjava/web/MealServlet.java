package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Storage.StorageListForMeals;
import ru.javawebinar.topjava.Storage.StorageMeals;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    private StorageMeals storageMeals;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storageMeals = new StorageListForMeals();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        Meal m = new Meal(id, dateTime, description, calories);
        storageMeals.update(m);

        log.debug("redirect to meals");
        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            List<Meal> meals = storageMeals.getAll();
            List<MealTo> mealsTo = MealsUtil.convertToMealsTo(meals, 2000);
            request.setAttribute("mealsTo", mealsTo);
            request.getRequestDispatcher("/WEB-INF/jsp/listMeals.jsp").forward(request, response);
            return;
        }
        Meal m;
        int newIntId;
        switch (action) {
            case "delete":
                newIntId = Integer.parseInt(id);
                storageMeals.delete(newIntId);
                response.sendRedirect("meals");
                return;
            case "add":
                m = new Meal(storageMeals.getCount(), LocalDateTime.now(), "", 0);
                toEditForm(request, response, m);
                return;
            case "update":
                newIntId = Integer.parseInt(id);
                m = storageMeals.get(newIntId);
                toEditForm(request, response, m);
                return;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
    }

    private void toEditForm(HttpServletRequest request, HttpServletResponse response, Meal m) throws ServletException, IOException {
        log.debug("Dispatcher");
        request.setAttribute("meal", m);
        request.getRequestDispatcher(
                "/WEB-INF/jsp/editMeal.jsp"
        ).forward(request, response);
    }
}