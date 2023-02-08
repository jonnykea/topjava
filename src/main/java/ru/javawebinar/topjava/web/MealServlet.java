package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Storage.ListMeals;
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
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private StorageMeals storageMeals;

    private static final Logger log = getLogger(MealServlet.class);

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storageMeals =  new ListMeals();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        AtomicInteger id = new AtomicInteger(Integer.parseInt(req.getParameter("id")));
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        Meal m;
        m = new Meal(id, dateTime, description, calories);
        storageMeals.update(m);

        log.debug("redirect to meals");
        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            List<Meal> meals = storageMeals.getAllSorted();
            List<MealTo> mealsTo = MealsUtil.convertToMealsTo(meals);
            request.setAttribute("mealsTo", mealsTo);
            request.getRequestDispatcher("/WEB-INF/jsp/listMeals.jsp").forward(request, response);
            return;
        }
        AtomicInteger id = new AtomicInteger(Integer.parseInt(request.getParameter("id")));
        Meal m;
        switch (action) {
            case "delete":
                storageMeals.delete(id);
                response.sendRedirect("meals");
                return;
            case "add":
                m = new Meal(storageMeals.size(),LocalDateTime.now(),"",0 );
                delegate(request, response, m);
                return;
            case "update":
                m = storageMeals.get(id);
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
