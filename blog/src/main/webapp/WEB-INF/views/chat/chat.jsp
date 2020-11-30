<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>Spring Boot WebSocket Chat Application</title>
<link rel="stylesheet" href="/css/chat.css" />
</head>
<body>
	<%@ include file="../layout/header.jsp"%>

	<div id="chat-page">
		<div class="chat-container">
			<div class="chat-header">
				<input type="hidden" id="matchUserNickname" value="${chatRoom.getMatchedUser().nickname }" />
				<h2>${chatRoom.getMatchedUser().nickname }님과대화</h2>
			</div>
			<div class="connecting">연결중...</div>
			<ul id="messageArea" style="background-color: #B2C7D9">
				<c:forEach var="message" items="${ chatRoom.messageList}">

					<c:choose>
						<c:when test="${ message.user.userno != user.userno}">
							<li class="chat-message-li"><span>${ message.user.nickname}</span>
								<div class="chat-message ">
									<p>${message.text }</p>
								</div>${ message.getFormatStr()}</li>
						</c:when>
						<c:otherwise>
							<li class="chat-message-li me">
								<div class="chat-message">
									<p>${message.text }</p>
								</div>${ message.getFormatStr()}</li>
						</c:otherwise>
					</c:choose>


				</c:forEach>
			</ul>
			<input type="hidden" id="nickname" value="${principal.user.nickname }"> <input type="hidden" id="userno" value="${principal.user.userno }"> <input type="hidden" id="chatRoomTopic" value="${chatRoom.topic}">
			<form id="messageForm" name="messageForm">
				<div class="form-group">
					<div class="input-group clearfix">
						<input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control" />
						<button type="submit" class="primary">보내기</button>
					</div>
				</div>
			</form>
			<form id="leaveForm" name="leaveForm">
				<button type="button" onclick="history.back()" class="primary">나가기</button>
			</form>
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

	<%@ include file="../layout/footer.jsp"%>
</body>
<script src="/js/chat/chat.js"></script>
</html>

