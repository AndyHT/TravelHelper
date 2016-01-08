$(document).ready(function() {
	$.ajax({
		type: "GET",
        url: 'http://localhost:8088/travel_helper/GetAllUsesrInfo',
        dataType: "json",
        success: function(data) {
            if (!data.result) {
                for (var i = 0; i<data.user.length;i++) {
                    $("#userInfo").append(
                    		"<tr>" +
                    		"<td>" + (i + 1) + "</td>" +
                    		"<td>" + data.user[i].userName + "</td>" +
                    		"<td>" + data.user[i].gender + "</td>" +
                    		"<td>" + data.user[i].email + "</td>" +
                    		"<td>" + data.user[i].registerDate + "</td>" +
                    		"</tr>"
                    );
                }
            }

            else {
                $("#userInfo").append(data.result);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            $("#userInfo").html(jqXHR.responseText);
        }
    });
});