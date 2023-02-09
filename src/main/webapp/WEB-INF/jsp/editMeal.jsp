<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<section>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value="${meal.id}">
        <h3>Edit meal:</h3>
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" name="dateTime" size=50
                       placeholder="example 01/30/2020 10:00" autofocus="autofocus" required="required"
                       value="${meal.dateTime}">
            </dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" name="description" size=50 required="required" value="${meal.description}"></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" name="calories" size=50 required="required" value="${meal.calories}"></dd>
        </dl>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
</body>
</html>