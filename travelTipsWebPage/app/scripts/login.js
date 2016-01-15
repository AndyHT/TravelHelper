$(document).ready(function() {
    $("#form-signin").submit(function(e){
           e.preventDefault();
    });

    $("#login-btn").click(function(e){
        var inputEmail = $("input#inputEmail").val(); 
        var password = $("input#inputPassword").val();
        $.ajax({
            type: "GET",
            url: 'http://localhost:8088/travel_helper/adminLogin?inputEmail=' + inputEmail + '&inputPassword=' + password,
            dataType: "json",
            success: function( data, textStatus, jqXHR) {
                if(data.result){
                    location.href ="http://localhost:9000/index.html"
                } 
                else {
                    $("#loginResult").html("login error");
                    setTimeout(function() {
                        $(':input','#form-signin') 
                        .not(':button, :submit, :reset') 
                        .val('');
                        $("#loginResult").html('');
                        $("#inputEmail").focus();
                    }, 2000);
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                console.log("Something really bad happened " + textStatus);
                console.log(XMLHttpRequest.status);
                console.log(XMLHttpRequest.readyState);
                console.log(textStatus);
            }
        }); 
    })
});