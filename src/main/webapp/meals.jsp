<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.util.List" %>
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
<table>
    <tr>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    <% if (request.getAttribute("meals") != null) {
        List<MealTo> meals = (List<MealTo>) request.getAttribute("meals");
        for (MealTo meal : meals) {%>
    <tr style="color: <%= meal.isExcess() ? "red" : "green"%>">
        <td><%=TimeUtil.formatDate(meal.getDateTime())%>
        </td>
        <td><%=meal.getDescription()%>
        </td>
        <td><%=meal.getCalories()%>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
