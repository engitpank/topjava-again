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
<c:if test="${requestScope.containsKey('meals')}">
    <a href="<c:url value="meals?action=create"/>">Добавить еду</a>
    <table>
        <tr>
            <th>Дата</th>
            <th>Описание</th>
            <th>Калории</th>
        </tr>
        <jsp:useBean id="meals" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
        <c:forEach items="${meals}" var="meal">
            <tr style="color: ${meal.excess ? "red" : "green"}">
                <td>${TimeUtil.formatDate(meal.dateTime)}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>
                    <a href="<c:url value="/meals?action=delete&id=${meal.ID}"/>">Удалить</a>
                </td>
                <td>
                    <a href="<c:url value="/meals?action=get&id=${meal.ID}"/>">Открыть</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${requestScope.containsKey('meal')}">
    <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
    <a href="<c:url value="/meals?action=update&id=${meal.id}"/>">Изменить еду</a>
    <p>Информация о еде</p>
    <p>Дата: ${TimeUtil.formatDate(meal.dateTime)}</p>
    <p>${meal.description}</p>
    <p>Калории: ${meal.calories}</p>
</c:if>
</body>
</html>
