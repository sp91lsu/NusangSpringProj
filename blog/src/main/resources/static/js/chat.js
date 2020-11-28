(function() {

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

	function connect() {

		console.log(chatRoomTopic);
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
				text : messageInput.value,
				topic : chatRoomTopic,
				userno : userno,
				nickname : nickname
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

		if (message.userno == $("#userno").val()) {
			messageElement = '<li class="chat-message-li me">'
					+ '<div class="chat-message">' + '<p>' + message.text
					+ '</p>' + '</div>' + message.createDate + '</li>';
		} else {
			messageElement = '<li class="chat-message-li"><span>'
					+ message.nickname + '</span>'
					+ '<div class="chat-message ">' + '<p>' + message.text
					+ '</p>' + '</div>' + message.createDate + '</li>';
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
})()