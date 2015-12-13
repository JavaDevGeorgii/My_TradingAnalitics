<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.06.2015
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="../static/css/jquery-ui.css"/>
<script src="../static/js/jquery-ui.min.js"></script>
<script src="../static/js/highcharts/highstock.js"></script>
<script src="../static/js/highcharts/exporting.js"></script>
<script src="../static/js/highcharts/currency_charts.js"></script>

<div class="parent">
  <div id="chart_panel" style="display: none; margin: 20px;">

    <div id="left_block">
      <div>
        <input type="checkbox" name="sma" id="sma" class="indicator"/>
        <label for="sma" class="text-indicator">SMA</label>
      </div>
      <div>
        <input id="sma_spinner" class="indicator-spinner" name="sma_spinner" size="3" value="5"/>
      </div>
    </div>

    <div id="right_block">
      <div>
        <input type="checkbox" name="ema" id="ema" class="indicator"/>
        <label for="ema" class="text-indicator">EMA</label>
      </div>
      <div>
        <input id="ema_spinner" class="indicator-spinner" name="ema_spinner" size="3" value="5"/>
      </div>
    </div>

  </div>



  <div id="container" style="width: 920px; height: 800px; "></div>

</div>
