var stompClient = null;

function connect(username) {
    var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({ username: username, }, function() {
        console.log('Web Socket is connected');
        stompClient.subscribe('/users/queue/messages', function(message) {
            //$("#message").text(message.body);
            showGreeting(message.body);
        });
    });
}

function showGreeting(message) {
    $("#message").append("<tr><td>" + message + "</td></tr>");
}

$(function() {
    $("form").on('submit', function(e) {
        e.preventDefault();
    });
    $("#connect").click(function() {
        connect($("#username").val());
    });
    $("#send").click(function() {
        stompClient.send("/app/hello", {}, $("#name").val());
    });
});