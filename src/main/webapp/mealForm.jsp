<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<form name="frmAddMeal" method="post" action="<c:url value="/meals"/>">
    <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
    <label>Укажите дату:
        <input type="datetime-local" value="${meal.dateTime}" name="dateTime">
    </label>
    <label>Введите описание:
        <input type="text" value="${meal.description}" name="description">
    </label>
    <label>Количество калорий:
        <input type="text" value="${meal.calories}" name="calories">
    </label>
    <label>
        <input style="display: none" type="text" readonly value="${meal.id}" name="mealId">
    </label>
    <input type="submit" value="submit">
</form>
</body>
</html>
