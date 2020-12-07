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

		updateList();

		// document.location.reload(true);
	}
	
	function updateList(){
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
									if (value.remainSawCnt > 0) {
										cntElement = '<div><h3><span class="badge badge-danger badge-pill">'
												+ value.remainSawCnt
												+ '</span></h3></div>'
									}
									var lastMessage = value.lastMessage;
									var updateDate = "";
									if(lastMessage.length > 7){
										lastMessage = lastMessage.substring(0, 7) + " ...";
									}
									if(lastMessage != "")
										{
										updateDate = '<h3>'+ value.updateDate +'</h3>'
										}
									var element = '<input type="hidden" class="room_topic" value="'
											+ value.topic
											+ '" />'
											+ '<li onclick="location.href=\'/chat/chatpage?chat_userno='
											+ value.matchedUserNo
											+ '\'" class="list-group-item d-flex justify-content-between align-items-center chatchat">'
											+ '<div class="user_img_wrapper">'
											+ '<img class="user_img" src="'+value.user.picture+'" alt="" />'
											+ '</div>'
											+ '<div class="middle-area">'
											+ '<div>'
											+ '<h3 class="user-nickname">'+value.matchedUserName+'</h3>'
											+ '</div>'
											+ '<div>'
											+ '<h3>'+lastMessage+'</h3></div>'
											+ '</div>'
											+ '<div>'
											+ updateDate
											+ cntElement + '</div></li>';

									
									/*
									 * <img class="user_img" src="${chatRoom.user.picture }" alt="" />
									 * <div>
									<h3 class="user-nickname">${chatRoom.user.nickname }</h3>
									<div><h3>${chatRoom.getLastMessage()}</h3></div>
								</div>
								<div>


									${chatRoom.updateDate}
									<c:if test="${chatRoom.getRemainSawCnt() > 0}">
										<h4>
											<span class="badge badge-danger badge-pill">${chatRoom.getRemainSawCnt()}</span>
										</h4>
									</c:if>
								</div>*/
									
									$(".list-group").append(element);

									console.log(value.remainSawCnt)
								});

			}

		})
	}
	updateList();
})()