/**
 * Created by User on 09.06.2015.
 */
$(document).ready(function () {

    getQuotes();
    updateNews();
    setTimeout(function run() {
        quotesUpdate();
        setTimeout(run, 3000);
    }, 3000);

    setTimeout(function run() {
        updateNews();
        setTimeout(run, 600000);
    }, 600000);

    $( ".graph" ).tooltip({
        hide: { effect: "explode", duration: 1000 }
    });

    $("#news").on("click", "#show_last_news", function (event) {
        event.preventDefault();
        getPreviousNews();
    });

    $("#news").on("click", "#show_next_news", function (event) {
        event.preventDefault();
        getNextNews();
    });
});

function getQuotes(){

    dust.isDebug = true;
    var template = $("#quotesTemplate").html();
    var compiled = dust.compile(template, "quotes_template");
    dust.loadSource(compiled);

    $.ajax({
        url:"/quotes",
        type: "GET",
        dataType: "json",
        success:function(data){
            var result ={"symbols":data};
            dust.render("quotes_template",result,function(err,out){
                $("#quotes").html(out);
            })
        }
    });
}

function quotesUpdate(){

    $.ajax({
        url:"/updateQuotes",
        dataType: "json",
        success: function(data){
            updatePair(data);
        }
    });
}

function updatePair(data){

    $.each(data, function(idx, obj) {
        var symbol = obj.symbol;
        var ratio = $("#"+symbol+"_RATIO").text();
        if(ratio > obj.ratio){
            $("#"+symbol+"_RATIO").html(obj.ratio).css('color', 'red').delay(500).animate({color:'black'},800);
            return true;
        }
        if(ratio < obj.ratio){
            $("#"+symbol+"_RATIO").html(obj.ratio).css('color', 'green').delay(500).animate({color:'black'},800);
            return true;
        }
    });
}



function updateNews() {

    dust.isDebug = true;
    var template = $("#newsTemplate").html();
    var compiled = dust.compile(template, "news_template");
    dust.loadSource(compiled);

    dust.helpers.formatDate = function (chunk, context, bodies, params) {
        var value = dust.helpers.tap(params.value, chunk, context),
            timestamp,
            month,
            date,
            year;

        timestamp = new Date(parseInt(value));
        month = timestamp.getMonth() + 1;
        date = timestamp.getDate();
        year = timestamp.getFullYear();

        return chunk.write(date + '.' + month + '.' + year);
    };

    $.ajax({
        url:"/getNews",
        type: "GET",
        dataType: "json",
        success:function(data){
            var result ={"news":data};
            dust.render("news_template",result,function(err,out){
                $("#news").html(out);
            })
        }
    });
}


function getPreviousNews() {

    dust.isDebug = true;
    var template = $("#newsTemplate").html();
    var compiled = dust.compile(template, "news_template");
    dust.loadSource(compiled);

    dust.helpers.formatDate = function (chunk, context, bodies, params) {
        var value = dust.helpers.tap(params.value, chunk, context),
            timestamp,
            month,
            date,
            year;

        timestamp = new Date(parseInt(value));
        month = timestamp.getMonth() + 1;
        date = timestamp.getDate();
        year = timestamp.getFullYear();

        return chunk.write(date + '.' + month + '.' + year);
    };

    var lastUrl = $("#table_news tr:last-child td:first-child a:first-child").attr("href");

    $.ajax({
        data : {lastUrl : lastUrl},
        url:"/getPreviousNews",
        type: "GET",
        dataType: "json",
        success:function(data){
            $("#news").effect( "drop", {}, 500, callback );
            var result ={"news":data};
            dust.render("news_template",result,function(err,out){
                $("#news").html(out);
            })
            $("#news").effect( "slide", {}, 500, callback );
        }
    });
}

function getNextNews() {
    dust.isDebug = true;
    var template = $("#newsTemplate").html();
    var compiled = dust.compile(template, "news_template");
    dust.loadSource(compiled);

    dust.helpers.formatDate = function (chunk, context, bodies, params) {
        var value = dust.helpers.tap(params.value, chunk, context),
            timestamp,
            month,
            date,
            year;

        timestamp = new Date(parseInt(value));
        month = timestamp.getMonth() + 1;
        date = timestamp.getDate();
        year = timestamp.getFullYear();

        return chunk.write(date + '.' + month + '.' + year);
    };

    var lastUrl = $("#table_news tr:first-child td:first-child a:first-child").attr("href");

    $.ajax({
        data : {lastUrl : lastUrl},
        url:"/getNextNews",
        type: "GET",
        dataType: "json",
        success:function(data){
            var result ={"news":data};
            if  (data.length > 0){
                $("#news").effect( "drop", {}, 500, callback );
                dust.render("news_template",result,function(err,out){
                $("#news").html(out);
                $("#news").effect( "slide", {}, 500, callback );
            })
            }
        }
    });
}

function callback() {
    setTimeout(function() {
        $( "#effect" ).removeAttr( "style" ).hide().fadeIn();
    }, 1000 );
};







