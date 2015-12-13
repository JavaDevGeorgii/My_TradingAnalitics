/**
 * Created by Alexandr Velikiy on 06.05.2015.
 */


$(document).ready(function () {

    if (this.URL.indexOf("/charts/countDealsByTool") != -1){
        $.ajax({
            url: "/charts/getChartDataCountDealsByTool",
            type: "POST",
            dataType: "JSON",
            success: function (data) {
                showChartCountDealsByTool(data);
            }
        })
    }

    else if (this.URL.indexOf("charts/pAndLByDay") != -1) {
        $.ajax({
            url: "/charts/getChartDataPAndLByDay",
            type: "POST",
            dataType: "JSON",
            success: function (data) {
                showChartPAndLByDay(data);
            }
        })
    }

});


function showChartCountDealsByTool(data) {

    var categories = new Array();
    var masData = new Array();
    var mas = new  Array();


    $.each(data, function(index, value ) {
        categories[index] = value.tool;
        masData[index] = value.countDeal;
        mas[index] = new Array();

        mas[index][0] = value.tool;
        mas[index][1] = value.countDeal;
    });

    chartCountDealsByTool = new Highcharts.Chart({
        chart: {
            renderTo: 'chartCountDealsByTool',
            type: 'column',
            height: 350
        },
        title: {
            text: 'Count deals by tools 1'
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            title: {
                text: 'Count of deal'
            }
        },
        series: [
            {
                name: 'Tool',
                data: masData
            }
        ]
    });


    roundChartCountDealsByTool = new Highcharts.Chart({
        chart: {
            renderTo: 'roundChartCountDealsByTool',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            height: 350
        },
        title: {
            text: 'Count deals by tools 2'
        },
        tooltip: {
            pointFormat: '<b>{point.percentage}%</b>',
            percentageDecimals: 1
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [
            {
                type: 'pie',
                name: 'Tool',
                data: mas
            }
        ]
    });
};

function showChartPAndLByDay(data) {

    var chartDataPAndLByDay = [];
    var chartDataPAndLCumulative = [];
    var cumulativePAndL = 0;

    $.each(data, function(index, value ) {
        chartDataPAndLByDay.push([value.date, value.profit]);
        cumulativePAndL += value.profit;
        chartDataPAndLCumulative.push([value.date, cumulativePAndL]);
    });

    var dataObject = {
        rangeSelector: {
            selected: 5
        },

        title: {
            text: 'P&L by day and cumulative'
        },

        series: [{
            name: 'P&L by day',
            data: chartDataPAndLByDay,
            color: '#0000FF',
            negativeColor: '#FF0000',
            tooltip: {
                valueDecimals: 2
            },
            type: 'column'
        },
        {
            name: 'Cumulative P&L',
            data: chartDataPAndLCumulative,
            lineWidth: 3,
            color: '#FFCC00',
            tooltip: {
                valueDecimals: 2
            },
            type: 'spline'
        }],

        chart: {
            renderTo: 'chartPAndLByDay',
            zoomType: 'x'
        }

    };

    new Highcharts.StockChart(dataObject);
};

function showChartPAndLByDay(data, tool) {

    var chartDataPAndLByDay = [];
    var chartDataPAndLCumulative = [];
    var cumulativePAndL = 0;


    $.each(data, function(index, value ) {
        chartDataPAndLByDay.push([value.date, value.profit]);
        cumulativePAndL += value.profit;
        chartDataPAndLCumulative.push([value.date, cumulativePAndL]);
    });

    if(tool==undefined)
        tool='TOTAL';

    var dataObject = {
        rangeSelector: {
            selected: 5
        },

        title: {
            text: 'P&L by day and cumulative'+' by '+tool
        },

        series: [{
            name: 'P&L by day',
            data: chartDataPAndLByDay,
            color: '#0000FF',
            negativeColor: '#FF0000',
            tooltip: {
                valueDecimals: 2
            },
            type: 'column'
        },
            {
                name: 'Cumulative P&L',
                data: chartDataPAndLCumulative,
                lineWidth: 3,
                color: '#FFCC00',
                tooltip: {
                    valueDecimals: 2
                },
                type: 'spline'
            }],

        chart: {
            renderTo: 'chartPAndLByTool',
            zoomType: 'x'
        }

    };

    new Highcharts.StockChart(dataObject);
};

function drawChartPandLbyTool(tool) {
    //(this.URL.indexOf("charts/pAndLByDay") != -1) {

    $("#chartPandLByTool").html(
        $.ajax({
            url: "getChartDataPAndLByDay" + "/{" + tool + "}",
            type: "GET",
            dataType: "JSON",
            success: function (data) {
                showChartPAndLByDay(data, tool);
            }
        })
    );

};
