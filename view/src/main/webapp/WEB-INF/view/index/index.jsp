<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.05.2015
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page isELIgnored ="false" %>

<link rel="stylesheet" href="../static/css/jquery-ui.css"/>
<script src="../static/js/dust-full.js"></script>
<script src="../static/js/main_page.js"></script>
<script src="../static/js/jquery-ui.min.js"></script>
<script src="../static/js/jquery.color-2.1.2.min.js"></script>
<script src="../static/js/dust-helpers.min.js"></script>
<%--<script src="../static/js/dust-intl.min.js"></script>--%>

<script type="text/dust" id="quotesTemplate">
<div>
 <table id="currency-table">
{#symbols}<tr id="{symbol}" >{~n}
<td id="{symbol}_CELL" style="font-weight: 600; text-align: left; width: 40%" >{symbol}</td>{~n}
<td id="{symbol}_RATIO" style="text-align: right; width: 40%">{ratio}</td>{~n}
<td style="text-align: right; width: 20%"><a href="charts/{symbol}" class="symbol"><span class="glyphicon glyphicon-stats" title="Move to the graph"></span></a>
</tr>
{/symbols}
 </tbody>
 </table>
</div>
</script>
<div id="quotes" style="margin:20px; padding: 20px;" class="col-md-3"></div>

<div id="someContent" style="margin:20px; padding: 20px;" class="col-md-5">some content</div>

<script type="text/dust" id="newsTemplate">
<div>
        <table  class="table table-hover">
            <thead>
                <th>Top News</th>
            </thead>
            <tbody>
                <tr>
                    <td> <a id="show_last_news" href="javascript:void(0)"> Previous </a> </td>
                    <td> <a id="show_next_news" href="javascript:void(0)"> Next  </a> </td>
                </tr>
            </tbody>
        </table>
     <table id = "table_news" cellspacing="3px" class="table table-hover">
        <tbody>
        {#news}
            <tr>
                <td>
                    <table>
                       <tr> <td padding = "7px";><a href={topicUrl}><img src={topicImageUrl} width="80px" class="img-rounded" ></a></td> </tr>
                       <tr align="center"> <td height="5px"><h6>{@formatDate value="{date}"/}</h6></td></tr>
                    </table>
                </td>
                 <td padding = "7px";>
                    <a href={topicUrl} class = "text-justify"> {topicName} </a>
                 </td>
             </tr>
         {~n}{/news}
        </tbody>
    </table>
</div>
</script>
<div id="news" style="margin:5px; padding: 5px;" class="col-md-3"></div>



