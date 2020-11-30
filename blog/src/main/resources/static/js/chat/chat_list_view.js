(function() {

	var stompClient = null;

	function connect() {

		var socket = new SockJS('/ws');
		stompClient = Stomp.over(socket);

		stompClient.connect({}, onConnected, onError);
	}
	connect();

	function onConnected() {
		console.log("내 닉넴으로 기다린다" + $("#user-nickname").val());
		stompClient.subscribe('/topic/' + $("#user-nickname").val(),
				onMessageReceived);
		/*
		 * $(".room_topic").each( function(index, item) { console.log(index + " + " +
		 * $(item).val()); stompClient.subscribe('/topic/' + $(item).val(),
		 * onMessageReceived); })
		 */

	}

	function onError(error) {
		console.log(error)
	}

	function onMessageReceived(payload) {
		
		
		console.log("updat_list_view " + payload.body)

		setTimeout(function(){
			
			$
			.ajax({

				url : "/api/chat/updat_list_view",
				type : "GET",
				success : function(res) {

					$(".list-group").empty();

					console.log(res)

					$
							.each(
									res,
									function(key, value) {
											
										var cntElement = "";
										if(value.remainSawCnt > 0){
											cntElement = '<span class="badge badge-primary badge-pill">'+
											value.remainSawCnt+'</span>'
										}
										
										var element = '<input type="hidden" class="room_topic" value="'+value.topic+'" />'
												+ '<li onclick="location.href=\'/chat/chatpage?chat_userno='+value.matchedUser+'\'" class="list-group-item d-flex justify-content-between align-items-center">'+value.matchedUserName
												+  cntElement
												+ '</li>';

										$(".list-group").append(element);
										
										console.log(value.remainSawCnt)
									});

					
				}

			})
			
		}, 100)
		
		// document.location.reload(true);
	}

})()