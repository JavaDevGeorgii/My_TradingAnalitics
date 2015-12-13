/**
 * Created by User on 12.05.2015.
 */

$(document).ready(function() {

    $('#loginUserModal').on('shown.bs.modal', function () {
        $('#check_login').focus();
    });


    $('#login_user').bind('click',  function() {
        event.preventDefault();
        var data = $("#login_form").serialize();
        $.ajax({
                url: "/login",
                type: "POST",
                data: data,
                success: function (data) {
                    document.write(data);
                },
                error: function () {
                    var errorMessage = "Check username or password";
                    $('#result_login').html(errorMessage).css('color', 'red').show().delay(3000).fadeOut("slow");
                }

            }
        );

    });
});

