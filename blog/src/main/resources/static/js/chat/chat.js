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
	
	//메세지 전달 ㅎ
	var updateReqTopic = '/topic/' + chatRoomTopic + ".update_req."+ nickname;
	var updateAckTopic = '/topic/' + chatRoomTopic + ".update_ack."+ nickname;
	
	function onConnected() {
		// Subscribe to the Public Topic
		stompClient.subscribe('/topic/' + chatRoomTopic, onMessageReceived);
		stompClient.subscribe(updateReqTopic, onMessageUpdateReqReceived);
		stompClient.subscribe(updateAckTopic, onMessageUpdateAckReceived);
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
				matchUser : matchUserNickname,
				updateAckTopic : updateAckTopic
			};

			stompClient.send("/app/chat.sendMessage", {}, JSON
					.stringify(chatMessage));

			messageInput.value = '';
		}
		event.preventDefault();
	}

	//메세지 업데이트 요청이 들어오면 메세지를 읽고 다시 상대방한데 업데이트 ack를 날린다. 
	function onMessageUpdateReqReceived(payload) {
		console.log("onMessageUpdateReqReceived " + payload.body)
		$.ajax({

			url : "/api/chat/update_read_message",
			type : "POST",
			headers : headers,
			data : {
				topic : payload.body,
				update_req : 1
			},
			success : function(res) {
				var chatMessage = {
						topic : '/topic/' + chatRoomTopic + ".update_ack."+ matchUserNickname
					};

				createElements(res)
				
				stompClient.send("/app/send_topic", {}, JSON
						.stringify(chatMessage));
			}
		})
	}

	function onMessageUpdateAckReceived(payload) {
		console.log("onMessageUpdateAckReceived " + payload.body)
		updateReadMessage(chatRoomTopic);
	}
	
	function onMessageReceived(payload) {
		console.log("onMessageReceived " + payload.body)
		updateReadMessage(payload.body);
	}


	function updateReadMessage(topic) {
		$
				.ajax({

					url : "/api/chat/update_read_message",
					type : "POST",
					headers : headers,
					data : {
						topic : topic,
						update_req : 0
					},
					success : function(res) {

						createElements(res)
					}
				})
	}

	
	function createElements(res){
		
		console.log(res);
		if (res.status == 200) {

			$(messageArea).empty();
			var beforeTop = messageArea.scrollTop;

			$
					.each(
							res.data,
							function(key, value) {

								var messageElement = document
										.createElement('li');
								
								var view_cnt = "";
								
								if(value.view_cnt > 0){
									view_cnt = value.view_cnt;
								}
								
								if (value.user.userno != $(
										"#userno").val()) {
									messageElement = '<li class="chat-message-li">' +
									'<div class="user_img_wrapper">' +
										'<img class="user_img" src="'+value.user.picture+'" alt="" />'+
										'</div>'+
										'<div class="oponent_user_chat_box">' +
										'<span>'+value.user.nickname+'</span>'+
									'<div>'+
									'<div class="chat-message">'+
										'<p class="message-text">'+ value.text+'</p>'+
									'</div>'+
									'<ul class="message-info-ul">'+
										'<li><span class="view_cnt">'+view_cnt+'</span></li>'+
									'<li><span class="view_date"> '+value.createDate+'</span></li>'+
									'</ul>'+
								'</div>'+
									'</div>'+
									'</li>';
										
								} else {
									messageElement = '<li class="chat-message-li me">'+
									'<div>'+
										'<div class="chat-message">'+
											'<p class="message-text">'+ value.text+'</p>'+
										'</div>'+
										'<ul class="message-info-ul">'+
											'<li><span class="view_cnt">'+view_cnt+'</span></li>'+
										'<li><span class="view_date"> '+value.createDate+'</span></li>'+
										'</ul>'+
									'</div></li>';
								}

								$(messageArea).append(
										messageElement);

							})

			console.log("af scrollTop" + messageArea.scrollTop);
			console.log(messageArea.scrollHeight);
			// if (messageArea.scrollTop == beforeHeight) {
			messageArea.scrollTop = messageArea.scrollHeight;
			// }
		}
	}
	
	messageForm.addEventListener('submit', sendMessage, true)
	
	
})()