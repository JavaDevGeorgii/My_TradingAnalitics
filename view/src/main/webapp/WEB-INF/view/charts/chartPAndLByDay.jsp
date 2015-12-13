<%--
  Created by IntelliJ IDEA.
  User: Vitalii Ievtushenko
  Date: 09.05.2015
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Charts page</title>

    <%--<link rel="stylesheet" href="../static/css/charts.css"/>--%>

    <script src="../static/js/jquery-1.11.2.min.js"></script>
    <script src="../static/js/highcharts/highstock.js"></script>
    <script src="../static/js/highcharts/highchartsMain.js"></script>

</head>
<body>

<%--<div id="chartPAndLByDay" class="chart"></div>--%>
<div style="text-align: center">
    <h2>Select tool for draw charts</h2>
</div>

<div class="row">
    <div class="col-lg-2" >
        <form id="boxOfTools" name="boxOfTools">


            <div>
                <table>
                    <%--<table style="margin: auto">--%>


                    <td>
                        <c:forEach var="Tools" items="${tools}">

                            <tr style="width: 20%; text-align: right ">
                                <td>
                                    <c:out value="${Tools.tool}"/>
                                    <input type="radio" name="toolsCheck" value="${Tools.tool}">
                                    </input>
                                </td>
                            </tr>

                        </c:forEach>
                    </td>
                </table>
            </div>


        </form>
    </div>
    <div class="col-lg-10 top" style="padding-top: 10px">
        <div id="chartPAndLByTool" class="chart"></div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">

    $("input:radio[name='toolsCheck']").change(function () {
        drawChartPandLbyTool($(this).val());
    });
</script>
