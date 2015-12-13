/**
 * Created by User on 11.05.2015.
 */

$(document).ready(function() {

    $('#createUserModal').on('shown.bs.modal', function () {
        $('#login').focus();
    });

    $('#add_user').bind('click',  function() {
        event.preventDefault();
        var data = {
            login: $("input[name='login']").val(),
            password: $("input[name='password']").val(),
            firstName: $("input[name='confpass']").val()
        };

        if($("input[name='password']").val() == $("input[name='confpass']").val() ){
            doAjax(data);

        }else{
            var errorMessage = "Passwords don't match";
            $('#pass_match').html(errorMessage).css('color', 'red').show().delay(2000).fadeOut("slow");
        }
    });
});

function doAjax(data) {
    $.ajax({
            url: "/registration",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (msg) {
                if (msg == "OK"){
                    var successMessage = "New user have been saved";
                    $('#result').html(successMessage).css('color', 'green').show();
                    $('#add_user').hide();
                    $('#close_button').hide();
                    $('#home_button').show();
                    $('#stay_button').show();
                }
                if (msg == "ERROR"){
                    var errorMessage = "New user not have been saved";
                    $('#result').html(errorMessage).css('color', 'red').show().delay(2000).fadeOut("slow");
                }
            },
            error: function () {
                var errorMessage = "Bad request";
                $('#result').html(errorMessage).css('color', 'red').show().delay(2000).fadeOut("slow");
            }

        }
    );
}
