let stompClient = null;

function connect() {
    const socket = new SockJS('/chat');
    stompClient = new StompJs.Client({
        webSocketFactory: () => socket,
        onConnect: (frame) => {
            console.log('Chat connected: ' + frame);
            stompClient.subscribe('/topic/chat', (message) => {
                const payload = JSON.parse(message.body);
                respondMessage(payload.content);
            });
        }
    });
    stompClient.activate();
}


function sendMessage() {
    const message = $('#input-message').val();
    const nickname = $('#nickname').val();

    $(".direct-chat-messages").append(`
        <div class="direct-chat-msg">
            <div class="direct-chat-infos clearfix">
                <span class="direct-chat-name float-left">${nickname}</span>
            </div>
            <div class="direct-chat-text">
                ${message}
            </div>
        </div>
    `);

    stompClient.publish({
        destination: '/app/hello',
        body: JSON.stringify({ content: message })
    });
}


function respondMessage(message) {
    $(".direct-chat-messages").append(`
        <div class="direct-chat-msg right">
            <div class="direct-chat-infos clearfix">
                <span class="direct-chat-name float-right">Bot</span>
            </div>
            <div class="direct-chat-text">
                ${message}
            </div>
        </div>
    `);
}

$(function () {
    connect();

    $("#chat-form").on('submit', function (e) {
        e.preventDefault();
        sendMessage();
    });
    $("#chat-form button").click(function(e) {
        sendMessage();
    });
});
