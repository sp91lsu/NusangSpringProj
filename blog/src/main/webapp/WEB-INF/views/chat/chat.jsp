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
				
					<c:set var="view_cnt" value="${message.view_cnt > 0 ? message.view_cnt : '' }"></c:set>
					<c:choose>
						<c:when test="${ message.user.userno != user.userno}">
							
							
							<li class="chat-message-li">
							
							<img class="user_img" src="${message.user.picture }" alt="" />
							<div class="oponent_user_chat_box">
							<span>${ message.user.nickname}</span>
								<div>
									<div class="chat-message">
										<p class="message-text">${message.text }</p>
									</div>
									<ul class="message-info-ul">
										<li><span class="view_cnt">${ view_cnt }</span></li>
										<li><span class="view_date"> ${ message.getFormatStr()}</span></li>
									</ul>
								</div>
								</div>
								</li>

						</c:when>
						<c:otherwise>
							<li class="chat-message-li me">
								<div>
									<div class="chat-message">
										<p class="message-text">${message.text }</p>
									</div>

									<ul class="message-info-ul">
										<li><span class="view_cnt">${ view_cnt }</span></li>
										<li><span class="view_date"> ${ message.getFormatStr()}</span></li>
									</ul>
									
									
								</div>

							</li>

						</c:otherwise>
					</c:choose>


				</c:forEach>
			</ul>
			<input type="hidden" id="nickname" value="${principal.user.nickname }"> <input type="hidden" id="userno" value="${principal.user.userno }"> <input type="hidden" id="chatRoomTopic" value="${chatRoom.topic}">
			<form id="messageForm" name="messageForm">
				<div class="form-group">
					<div class="input-group clearfix">
						<input type="text" id="message"  autocomplete="off" class="form-control" />
						<button id="chat_btn_common" type="submit" class="primary">전송</button>
					</div>
				</div>
			</form>
			<form id="leaveForm" name="leaveForm">
				<button id="chat_btn_common" type="button" onclick="history.back()" class="primary">돌아가기</button>
			</form>
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

	<%@ include file="../layout/footer.jsp"%>
</body>
<script src="/js/chat/chat.js"></script>
</html>

