<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:useBean id="mealsTo" scope="request" type="java.util.List"/>
    <title>Meals</title>
</head>
<body>
<section>
    <a href="meals?uuid=${mealTo.uuid}&action=add"> Create new Meal <img src="img/add.png"></a>
    <br>
    <table border="1" cellpadding="8" cellspacing="0" style="margin: auto">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${mealsTo}" var="mealTo">
            <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr style="color: ${mealTo.excess ? 'red' : 'green' }">
                <td align="center">${mealTo.dateTime}</td>
                <td align="center">${mealTo.description}</td>
                <td align="center">${mealTo.calories}</td>
                <td align="center"><a href="meals?uuid=${mealTo.uuid}&action=update"><img src="img/pencil.png"></a>
                </td>
                <td align="center"><a href="meals?uuid=${mealTo.uuid}&action=delete"><img src="img/delete.png"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>