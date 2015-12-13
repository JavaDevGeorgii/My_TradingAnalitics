<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.05.2015
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
  <title><tiles:getAsString name="main.title"></tiles:getAsString></title>

  <link rel="stylesheet" href="../static/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="../static/css/bootstrap-theme.min.css"/>
  <link rel="stylesheet" href="../static/css/main.css"/>
  <script src="../static/js/jquery-1.11.2.min.js"></script>
  <script src="../static/js/bootstrap.min.js"></script>
  <script src="../static/js/user_add.js"></script>
  <script src="../static/js/login.js"></script>


</head>
<body>
<div id="header">
  <tiles:insertAttribute name="main.header"></tiles:insertAttribute>
</div>

<div id="content">
  <tiles:insertAttribute name="main.content"></tiles:insertAttribute>
</div>

<footer>
  <tiles:insertAttribute name="main.footer"></tiles:insertAttribute>
</footer>


</body>
</html>
