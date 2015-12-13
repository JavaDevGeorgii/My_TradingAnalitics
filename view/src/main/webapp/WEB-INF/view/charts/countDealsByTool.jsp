<%--
  Created by IntelliJ IDEA.
  User: Alexandr Velikiy
  Date: 06.05.2015
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Charts page</title>

    <link rel="stylesheet" href="../static/css/charts.css"/>
    <link rel="stylesheet" href="../static/css/bootstrap.min.css"/>

    <script src="../static/js/jquery-1.11.2.min.js"></script>
    <script src="../static/js/highcharts/highcharts.js"></script>
    <script src="../static/js/highcharts/highchartsMain.js"></script>
</head>
<body>

<div>
    <table class="table table-hover" class="table table-condensed">
        <thead>
            <tr>
                <td><strong>Tool</strong></td>
                <td><strong>Count</strong></td>
            </tr>
        </thead>
        <c:forEach var="chartData" items="${data}">
            <tr>
                <td><c:out value="${chartData.tool}" /></td>
                <td><c:out value="${chartData.countDeal}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div id="chartCountDealsByTool" class="chart"></div>
<div id="roundChartCountDealsByTool" class="chart"></div>
</body>
</html>
