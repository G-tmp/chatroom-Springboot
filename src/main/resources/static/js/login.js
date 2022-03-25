
function login() {
    var nickname = $("#nickname").val();

    var data = {
        nickname: nickname
    };

    $.ajax({
        type: "post",
        url: "/login",
        async:false,
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function (result) {
            // alert(JSON.stringify(result));
            if (result.code === 0) {
                window.location.href = window.location.protocol + '//' + window.location.host + '/';
            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("error");
        }
    });
}