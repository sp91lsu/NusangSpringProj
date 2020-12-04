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
	var matchUserNickname = document.querySelector('#matchUserNickname').value
			.trim();

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
				nickname : nickname,
				matchUser : matchUserNickname
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

		$.ajax({

			url : "/api/chat/update_read_message",
			type : "POST",
			headers : headers,
			data : {
				topic : payload.body
			},
			success : function(res) {

				console.log(res);
				if (res.status == 200) {

					$(messageArea).empty();
					 var beforeTop = messageArea.scrollTop;
					 
					 
					$.each(res.data, function(key, value) {
												
						 var messageElement = document.createElement('li');
						 if (value.user.userno == $("#userno").val()) {
						 messageElement = '<li class="chat-message-li me">'
						 + '<div class="chat-message"><p>'
						 + value.text + '</p></div>'
						 + value.createDate + '</li>';
						 } else {
						 messageElement = '<li class="chat-message-li"><span>'
						 + value.user.nickname + '</span>'
						 + '<div class="chat-message ">' + '<p>'
						 + value.text + '</p>' + '</div>'
						 + value.createDate + '</li>';
						 }
						 messageElement += value.view_cnt;
						
						
						
						 $(messageArea).append(messageElement);
						
													
					})
					
					 console.log("af scrollTop" + messageArea.scrollTop);
							console.log(messageArea.scrollHeight);
						// if (messageArea.scrollTop == beforeHeight) {
						 messageArea.scrollTop = messageArea.scrollHeight;
						// }
				}
			}
		})

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