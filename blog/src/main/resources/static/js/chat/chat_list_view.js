(function() {

	var connectingElement = document.querySelector('.connecting');

	var stompClient = null;

	function connect() {

		var socket = new SockJS('/ws');
		stompClient = Stomp.over(socket);

		stompClient.connect({}, onConnected, onError);
	}
	connect();

	function onConnected() {
		console.log("내 닉넴으로 기다린다" +$("#user-nickname").val());
		stompClient.subscribe('/topic/' + $("#user-nickname").val(),
				onMessageReceived);
		$(".room_topic").each(
				function(index, item) {
					console.log(index + " + " + $(item).val());
					stompClient.subscribe('/topic/' + $(item).val(),
							onMessageReceived);
				})

		connectingElement.classList.add('hidden');
	}

	function onError(error) {
		connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
		connectingElement.style.color = 'red';
	}

	function onMessageReceived(payload) {
		console.log("메세지 들어와땅 " + payload.body)

		document.location.reload(true);
	}

})()