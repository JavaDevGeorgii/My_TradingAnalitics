/**
 * Created by GEO on 11.05.15.
 */
var dataSet = ['-', 'tool', 'quantity', 'action', 'openPrice', 'closePrice', 'openTime', 'closeTime'];
var selectedColumn = [];
var selectedRow = [];

$(document).ready(function () {

    $("form input[type=file]").on('change', function () {
        var ddd = $("#file").val();
        if (ddd != 0) {
            $("#buttonUploadDB").removeAttr("hidden");
        }
    });

});


function run(columnNumber) {

    //get number of position in droplist item`s using selector select
    var currentSelectedRowInColumn =
        $("select[name='selectDropList'] :selected ")[columnNumber].index;


    for (var num = 0; num < (selectedColumn.length); num++) {
        if (selectedColumn[num] == columnNumber) {
            selectedColumn.splice(num, 1);
            selectedRow.splice(num, 1);
            if (currentSelectedRowInColumn == 0) {
                return;
            } else {
                break;
            }
        }
    }
    selectedColumn.push(columnNumber);
    selectedRow.push(currentSelectedRowInColumn);
}

function openDocument() {

    $("#buttonUploadDB").removeAttr("hidden");

    $("#formUpload").ajaxForm({
        success: function (data) {
            updateViewData(data);

            $.each(data, function (i, val) {
                $("#result").html(val);
            });
        },
        dataType: "json"
    }).submit();

    $("#buttonUploadDB").attr("disabled", true);

}

function updateViewData(data) {

    var arr = [];


    drawTable(data);


    function drawTable(data) {

        var end = (data.length > 15) ? 15 : data.length;
        for (var i = 0; i < end; i++) {
            drawRow(data[i], i);
        }
    }

    function drawRow(rowData, position) {

        var row = $("<tr />");

        $("#table-result-2").append(row);

        if (position == 0) {

            for (j = 0; j < rowData.length; j++) {
                row.append($("<td class='td1' style='text-align: center'>" + "<select name='selectDropList' id=selectList" + j + " role='listbox' onchange='run(" + j + ")'>" + "</td>"));

            }
            $("#table-result-2").append(row);

            row = $("<tr />");

            $("#table-result-2").append(row);

            var selectText;
            for (var i = 0; i < dataSet.length; i++) {
                selectText = "<option value=\"" + i + "\" >" + dataSet[i] + "</option>";
                $('select[name="selectDropList"]').append(selectText);
            }

            for (j = 0; j < rowData.length; j++) {
                row.append($("<th class='th1'> <nobr>" + rowData[j] + "</nobr> </th>"));
            }
            $("#table-result-2").append(row);

        } else {

            for (j = 0; j < rowData.length; j++) {
                row.append($("<td class='td1'> <nobr>" + rowData[j] + "</nobr> </td>"));
            }
            $("#table-result-2").append(row);
        }

    }

    $("#buttonSandToDB").removeAttr("hidden");
    $("#selectDateFormat").removeAttr("hidden");
    $("#selectTimeFormat").removeAttr("hidden");
    $("#tableSelectDateFormat").removeAttr("hidden");
}

function sandToDB() {

    var requestJSON = [];
    var k1 = [];
    var k2 = [];
    var dateFormatTemplate = $('select [name="selectOptionDate"]:selected').val() + $('select [name="selectOptionTime"]:selected').val();

    for (var i = 0; i < selectedColumn.length; i++) {
        k1[i] = dataSet[selectedRow[i]];
        k2[i] = selectedColumn[i];
    }

    var isDublication;
    var counterItemName;
    for(var entry=0;entry<k1.length;entry++){
        counterItemName=k1[entry];
        isDublication=0;

        for(var j=0;j<k1.length;j++){
            if(counterItemName==k1[j]){
                isDublication++;
            }
        }

        if(isDublication>1){
            alert("Detected duplication in selected data! Remove the extra data and repeat to 'Send Data' ");
            return;
        }
    }

    requestJSON = {
        item: k1.toString(),
        column: k2.toString(),
        selectDateFormat: dateFormatTemplate.toString()
    };

    var data = JSON.stringify(requestJSON);
    var arr = selectedColumn.toString();

    if (selectedColumn.length < 7) {
        alert("Please, select data!");
        return;
    } else if (selectedColumn.length > 7) {
        alert("You selected twice some columns. Please, check your selection!\nRemove the extra data, thanks.");
        return;
    } else {
        var outdata = 1;
        $.ajax({
            data: requestJSON,
            url: "/deal/uploadFile",
            type: 'POST',
            success: function (outdata) {
                //Responce.text();
                $("#output_message").text("В Базу загружено cтрок: " + outdata);

            }
        });
    }

    $("#buttonUploadDB").hide();
    $("#file").hide();

    $("#table-result-2").hide();
    $("#buttonSandToDB").hide();
    $("#selectDateFormat").hide();
    $("#selectTimeFormat").hide();
    $("#tableSelectDateFormat").hide();


}