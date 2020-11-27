'use strict';

var chatPage = document.querySelector('#chat-page');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
messageArea.scrollTop = messageArea.scrollHeight;
var connectingElement = document.querySelector('.connecting');
var leaveForm = document.querySelector('#leaveForm');

var stompClient = null;
var nickname = document.querySelector('#nickname').value.trim();
var chatRoomTopic = $("#chatRoomTopic").val();

var userno = document.querySelector('#userno').value.trim();
var colors = [ '#2196F3', '#32c787', '#00BCD4', '#ff5652', '#ffc107',
		'#ff85af', '#FF9800', '#39bbb0' ];

function connect() {

	console.log(chatRoomTopic)
	if (chatRoomTopic) {

		var socket = new SockJS('/ws');
		stompClient = Stomp.over(socket);

		stompClient.connect({}, onConnected, onError);
	}
}
connect();

function onConnected() {
	// Subscribe to the Public Topic
	stompClient.subscribe('/topic/' + chatRoomTopic, onMessageReceived);

	// Tell your username to the server
	stompClient.send("/app/chat.addUser", {}, JSON.stringify({
		sender : nickname,
		type : 'JOIN'
	}))

	connectingElement.classList.add('hidden');
}

function onError(error) {
	connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
	connectingElement.style.color = 'red';
}

function sendMessage(event) {
	var messageContent = messageInput.value.trim();
	if (messageContent && stompClient) {
		var chatMessage = {
			sender : nickname,
			content : messageInput.value,
			type : 'CHAT',
			subscribe : chatRoomTopic,
			"userno" : userno
		};
		stompClient.send("/app/chat.sendMessage", {}, JSON
				.stringify(chatMessage));
		messageInput.value = '';
	}
	event.preventDefault();
}

function leave(event) {
	if (messageContent && stompClient) {
		var leave = {
			sender : nickname,
			type : 'LEAVE'
		};
		stompClient.send("/app/chat.leave", {}, JSON.stringify(leave));
		messageInput.value = '';
	}
	event.preventDefault();
}

function onMessageReceived(payload) {
	console.log("메세지 들어와땅 " + payload.body)
	var message = JSON.parse(payload.body);

	var messageElement = document.createElement('li');

	if (message.type === 'JOIN') {
		messageElement.classList.add('event-message');
		message.content = message.sender + ' 님이 입장하였습니다.';
	} else if (message.type === 'LEAVE') {
		messageElement.classList.add('event-message');
		message.content = message.sender + ' 님이 나갔습니다.';
	} else {

		if (message.userno == $("#userno").val()) {
			messageElement = '<li class="chat-message me"><div>' + '<p>'
					+ message.content + '</p>' + '</div></li>';
		} else {
			messageElement = '<span>' + message.sender + '</span>'
					+ '<li class="chat-message "><div>' + '<p>'
					+ message.content + '</p>' + '</div></li>';
		}

	}

	$(messageArea).append(messageElement);
	messageArea.scrollTop = messageArea.scrollHeight;
}

function getAvatarColor(messageSender) {
	var hash = 0;
	for (var i = 0; i < messageSender.length; i++) {
		hash = 31 * hash + messageSender.charCodeAt(i);
	}
	var index = Math.abs(hash % colors.length);
	return colors[index];
}

messageForm.addEventListener('submit', sendMessage, true)
leaveForm.addEventListener('submit', leave, true)
