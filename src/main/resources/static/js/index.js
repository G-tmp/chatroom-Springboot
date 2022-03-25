function onloadIndex() {
    $.ajax({
        type: "get",
        url: "/loadIndex",
        dataType: "json",
        success: function (result) {
            console.log(result);
            if (result.code === 0) {
                var roomList = $("#room-list");
                roomList.append('<h3>room list</h3>');
                for (const room of result.data.roomList) {
                    roomList.append(
                        '<div class="card">\n' +
                        '        <div class="card-body">\n' +
                        '            <h4 class="card-title">' +
                        room.name +
                        '<span class="badge badge-secondary">' +
                        room.onlineCount + '/' + room.limitCount +
                        '</span></h4>\n' +
                        '            <a href="/room/' + room.id + '"' +
                        'class="card-link">Enter</a>\n' +
                        '        </div>\n' +
                        '    </div>');
                }

            } else {
                alert(result.msg);
            }
        },
        error: function () {
            alert("error");
        }
    });
}