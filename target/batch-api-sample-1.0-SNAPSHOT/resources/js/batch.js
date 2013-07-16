$(document).ready(function() {

    //request notifications permission
    $("#settings-btn").click(function() {
        if (window.webkitNotifications.checkPermission() != 0) { // 0 is PERMISSION_ALLOWED
            window.webkitNotifications.requestPermission();
        }
    });

    
    var session = new WebSocket("ws://" + document.location.host + 
            requestContextPath + "/ws/jobMonitor");

    session.onmessage = function(e) {
        
        var message = JSON.parse(e.data);
        console.log(message);
        if(message.type == 'IMAGE') {
            var img = document.createElement("img");
            img.src = requestContextPath +"/images" + message.body;
            img.width = '200';
            img.height= '200';
            document.getElementById("images").appendChild(img);
        } else {
            window.webkitNotifications.createNotification(
                    '', message.title, message.body).show();
        }
    };
    

});
