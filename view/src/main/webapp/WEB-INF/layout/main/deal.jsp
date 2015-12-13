<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: GEO
  Date: 08.05.15
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="now" class="java.util.Date" scope="page"/>


<link rel="stylesheet" type="text/css"
      href="../static/css/jquery.dataTables.css">

<link rel="stylesheet" type="text/css" href="../static/css/deal.css">


<link rel="stylesheet" href="../static/css/bootstrap.min.css"/>
<link rel="stylesheet" href="../static/css/bootstrap-theme.min.css"/>
<script src="../static/js/bootstrap.min.js"></script>

<script type="text/javascript" charset="utf8" src="../static/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8"
        src="../static/js/jquery.dataTables.min.js"></script>


<script src="../static/js/deal_JS.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>


<html>
<head>
    <title></title>
</head>
<body>
<div>
    <p>
        <br>
    </p>
</div>

<div>
    <h3>
        <p id="output_message"></p>
    </h3>
</div>

<form id="formUpload" method="post" action="/deal/send_file_path" enctype="multipart/form-data">
    <table>
        <thead>
        <th>
            <tr>
                <td>
                    <input id="file" name="file" type="file"/>
                </td>
                <td>
                    <input type="submit" value=" Upload Data to DataBase " hidden="hidden"/>
                </td>
        </thead>
    </table>
</form>

<button name="buttonUploadDB" id="buttonUploadDB" onclick="openDocument()" hidden="hidden"> Open document !</button>
<hr>
<table id="tableSelectDateFormat" hidden="hidden">
    <tr>
        <td>Before send data to DataBase, please, choose correct format type of DATE :</td>
        <td>
            <select id="selectDateFormat" role="listbox" hidden="hidden">
                <option name="selectOptionDate" value="yyyy/MM/dd" selected> YYYY/MM/DD</option>
                <option name="selectOptionDate" value="yyyy/MM/dd"> YYYY-MM-DD</option>
                <option name="selectOptionDate" value="MM/dd/yyyy"> MM/DD/YYYY</option>
                <option name="selectOptionDate" value="MM-dd-yyyy"> MM-DD-YYYY</option>
            </select>
        </td>
        <td> ... and TIME  :</td>
        <td>
            <select id="selectTimeFormat" role="listbox" hidden="hidden">
                <option name="selectOptionTime" value=" HH:mm" selected> HH:mm </option>
                <option name="selectOptionTime" value=" HH:mm:ss"> HH:mm:ss </option>
                <option name="selectOptionTime" value=" HH:mm:ss:SSS"> HH:mm:ss:SSS </option>
            </select>

        </td>
    </tr>
</table>

<div>
    <p>
        <br>
    </p>

    <button name="buttonSandToDB" id="buttonSandToDB" onclick="sandToDB()" hidden="hidden"> Send data to DB!</button>
</div>
<br>

<div id="result" <%--style="white-space: nowrap"--%>>
    <table id="table-result-2" class="table table-bordered table-hover table-condensed">
    </table>
</div>


</body>
</html>
