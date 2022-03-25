var ws = null;
var channel = null;

function onloadRoom() {
    var roomId = window.location.pathname.split('/').pop();

    $.ajax({
        type: "get",
        url: "/room/load",
        dataType: "json",
        data: {roomId: roomId},
        success: function (jsonRes) {
            console.log(jsonRes);
            if (jsonRes.code === 0) {
                $('#roomName').val(jsonRes.data.room.name);
                channel = jsonRes.data.channel;
                wsconnect('ws://' + window.location.host + channel);
            } else {
                alert(jsonRes.msg);
            }
        },
        error: function () {
            alert("error");
        }
    });
}

function wsconnect(wsURL) {
    ws = new WebSocket(wsURL);

    ws.onmessage = function(message) {
        append_message(message.data);
    };

    ws.onopen = function (ev) {
        console.log("on open");
        // ws.send();
    };

    ws.onclose = function (ev) {
        wsdisconnect();
    }
}

function wsdisconnect() {
    if (ws != null) {
        ws.close();
        ws = null;
    }
    console.log("Websocket is in disconnected state");
}

function sendData() {
    if (ws == null) {
        alert('connection closed!');
        return;
    }

    message = $("#msg").val();
    if (message.trim().length === 0) {
        return;
    }

    var data ={
        'msg' : message,
        'channel': channel,
        'command': 8
    };
    $("#msg").val('');
    ws.send(JSON.stringify(data));
}

function append_message(message) {
    message = JSON.parse(message);
    console.log(message);
    var msg = $("#msg-list");

    var xor = message.type | message.command;
    switch (xor) {
        case 9:     // system send
            break;
        case 10:    //owner send
            msg.prepend('<a class="list-group-item list-group-item-action text-success"><strong>' + message.sender + '</strong>' + ' : ' +  message.msg + '</a>');
            break;
        case 12:    // others send
            msg.prepend('<a class="list-group-item list-group-item-action"><strong>' + message.sender + '</strong>' + ' : ' +  message.msg + '</a>');
            break;
        case 18:    //owner enter
            msg.prepend('<p>\n' +
                '<div class="alert alert-success">\n' +
                'welcome! '+ '<strong>' + message.sender + '</strong> \n' +
                '</div>');
            break;
        case 20:    // others enter
            msg.prepend('<p>\n' +
                '<div class="alert alert-success">\n' +
                '<strong>' + message.sender + '</strong> \n' + ' enter' +
                '</div>');
            break;
        case 34:    // owner leave
            break;
        case 36:    // others leave
            msg.prepend('<p>\n' +
                '<div class="alert alert-success">\n' +
                '<strong>' + message.sender + '</strong> leave \n' +
                '</div>');
            break;
        default:
            alert('unexpected result');
    }
}


// $(function() {
//     $("form").on('submit', function(e) {
//         e.preventDefault();
//     });
//     $("#connect").click(function() {
//         connect();
//     });
//     $("#disconnect").click(function() {
//         disconnect();
//     });
//     $("#send").click(function() {
//         sendData();
//     });
// });

// window.onbeforeunload = function(event) {
//     console.log("close WebSocket ！");
//     wsdisconnect();
// };
//
// $(document).ready(function () {
//     console.log(sessionStorage.getItem('status'));
//     if (sessionStorage.getItem('status') != null && ws==null) {
//         wsconnect();
//     }
//
//     display_message(0);
// });