/**
 * Created by User on 15.06.2015.
 */

var flag = false;
var min = 0;
var closings = [];
var path = $(location).attr('pathname');
var symbol = path.substr(path. lastIndexOf('/') + 1);

$(document).ready(function () {

    drawGraph();

    setTimeout(function run() {
        drawGraph();
        getDetails();
        setTimeout(run, 35 * 1000);
    }, 35 * 1000);
});


function showToolGraph(data){

    var ohlc = dataFormatting(data);

    var  min =  data.ranges.close.min;

    $('#container').highcharts('StockChart', {

        rangeSelector: {
            buttons: [{
                type: 'hour',
                count: 1,
                text: '1 hour'
            },{
                type: 'hour',
                count: 3,
                text: '3 hour'
            },{
                type: 'hour',
                count: 6,
                text: '6 hour'
            }, {
                type: 'all',
                text: 'day'
            }],
            inputDateFormat: '%d.%m.%Y',
            inputEditDateFormat: '%d.%m.%Y',
            buttonTheme: {
                width: 60
            },
            selected: 0
        },

        title: {
            text: symbol
        },

        plotOptions: {
            candlestick: {
                color: 'red',
                upColor: 'green'
            },
            lineWidth: 1
        },

        yAxis : {
            plotLines : [{
                value : min,
                color : 'blue',
                dashStyle : 'dash',
                width : 1
            }]
        },
        legend: {
            align: 'center',
            verticalAlign: 'bottom',
            layout: 'horizontal',
            enabled: true,
            x: 0,
            y: 0,
            itemStyle: {
                color: '#333333',
                fontWeight: 'normal'
            }
        },

        series: [{
            type: 'candlestick',
            name: symbol,
            data: ohlc,
            dataGrouping: {
                units: [
                    [
                        'second',
                        [1, 2, 5, 10, 15, 30]
                    ], [
                        'minute',
                        [1, 2, 5, 10, 15, 30]
                    ], [
                        'hour',
                        [1, 2, 3, 4, 6, 8, 12]
                    ]]
            }
        }]
    }, function(){

        flag = true;

        $("#chart_panel").show();
        chooseIndicator();
        getDetails();
    });
}


Highcharts.setOptions({
    global: {
        useUTC: false
    }
});


function drawGraph(){
    $.ajax({
        type: "GET",
        url: "http://chartapi.finance.yahoo.com/instrument/1.0/" + symbol + "=X" +
        "/chartdata;type=quote;range=1d/json",

        dataType: "jsonp",
        contentType: "application/json",
        crossDomain: true,
        cache: true,
        success: function (data) {

            closings = getClose(data);

            if(flag){
                updateGraphs(data);
            }else{
                showToolGraph(data);
            }
        }
    })
}

function getDetails(){
    $.ajax({
        type: "GET",
        url: "http://finance.yahoo.com/webservice/v1/symbols/" + symbol + "=X" +
        "/quote?format=json&view=‌​detail",
        dataType: "jsonp",
        contentType: "application/json",
        crossDomain: true,
        cache: true,
        success: function (data) {

            $("div.index").remove();

            var change = data.list.resources[0].resource.fields.change;
            var chg_percent = data.list.resources[0].resource.fields.chg_percent;
            var day_high = data.list.resources[0].resource.fields.day_high;
            var day_low = data.list.resources[0].resource.fields.day_low;
            var year_high = data.list.resources[0].resource.fields.year_high;
            var year_low = data.list.resources[0].resource.fields.year_low;

            var left_content = "<div class='text-details'>Change</div>" + "<div>" + change + "</div>"+
                               "<div class='text-details'>Day high</div>" + "<div>" + day_high + "</div>"+
                               "<div class='text-details'>Year high</div>" + "<div>" + year_high + "</div>";

            var right_content = "<div class='text-details'>%Change</div>" + "<div>" + chg_percent + "</div>"+
                                "<div class='text-details'>Day low</div>" + "<div>" + day_low + "</div>"+
                                "<div class='text-details'>Year low</div>" + "<div>" + year_low + "</div>";
            $("#left_block").append("<div class='index'>" + left_content + "</div>");
            $("#right_block").append("<div class='index'>" + right_content + "</div>");
        }
    })
}

function chooseIndicator(){

    $(".indicator").click(function () {

        var id = $(this).attr('id');
        var chart = $('#container').highcharts();

        if($('input:checkbox[name=' + id + ']').is(':checked')){
            var period = $("#" + id + "_spinner").spinner( "value" );
            var data = (function(){
                if(id == 'sma'){return getSmaPoints(closings,period) } ;
                if(id == 'ema'){return getEmaPoints(closings,period) } ;
            }());

            var series = {
                id: id,
                name: id,
                lineWidth: 1.4,
                data: data
            }
            chart.addSeries(series)
        }else{
            chart.get(id).remove();
        }
    });
}

function dataFormatting(data){

    var ohlc = [];

    for (var i = 0; i < data.series.length; i++) {
        ohlc.push([
            (data.series[i].Timestamp) * 1000,
            data.series[i].open,
            data.series[i].high,
            data.series[i].low,
            data.series[i].close
        ]);
    }

    return ohlc;
}

function updateGraphs(data){
    var ohlc = dataFormatting(data);
    var chart = $('#container').highcharts();
    var series = chart.series;
    var seriesLength = series.length;
    series[0].setData(ohlc, true);
    for(var i = 1; i < seriesLength ; i++)
    {
        if(chart.series[i].name == "sma"){
            var period = $("#sma_spinner").spinner( "value" );
            series[i].setData(getSmaPoints(closings,period),true);
            continue;
        }
        if(chart.series[i].name == "ema"){
            var period = $("#ema_spinner").spinner( "value" );
            series[i].setData(getEmaPoints(closings,period),true)
        }
    }
}

function getClose(data){
    var arr = [];

    for (var i = 0; i < data.series.length; i++) {
        arr.push([
            data.series[i].Timestamp * 1000,
            data.series[i].close
        ]);
    }

    return arr;
}

function getSmaPoints(data,period){
    var arr =[];
    var sum = 0;
    var start =0;
    for(var i = 0; i < period; i++){
        sum += data[i][1];
    }
    var sma1 = sum / period;
    arr.push([data[period-1][0],+sma1.toFixed(4)]);
    var tmp = sma1;
    for(var i = period; i < data.length; i++){
        var sma = tmp - data[start][1]/period + data[i][1]/period;
        arr.push([
            data[i][0],
            +sma.toFixed(4)
        ]);
        tmp = sma;
        start++;
    }
    return arr;
}


function getEmaPoints(data,period){
    var arr = [];
    var sum = 0;
    var alfa = 2 / (period + 1);
    for(var i = 0; i < period; i++){
        sum += data[i][1];
    }
    var ema1 = sum / period;
    arr.push([data[period-1][0],+ema1.toFixed(4)]);
    var tmp = ema1;
    for(var i = period; i < data.length; i++){
        var ema = alfa * data[i][1] + (1 - alfa) * tmp;
        arr.push([
            data[i][0],
            +ema.toFixed(4)
        ]);
        tmp = ema;
    }
    return arr;
}

$(function() {
    $('.indicator-spinner').spinner({
        min: 5,
        max: 21,
        step: 1
    });
});


