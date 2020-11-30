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
	<input type="hidden" id="user-nickname" value="${user.nickname }" />
	<h2>채팅 리스트</h2>
	<ul class="list-group">
		<c:forEach var="chatRoom" items="${chatRoomList }">
			<input type="hidden" class="room_topic" value="${chatRoom.topic }" />
			<li onclick="location.href='/chat/chatpage?chat_userno=${chatRoom.getMatchedUser().userno}'" class="list-group-item d-flex justify-content-between align-items-center">${chatRoom.getMatchedUserName() }
			<c:if test="${chatRoom.getRemainSawCnt() > 0}">
			<span class="badge badge-primary badge-pill">${chatRoom.getRemainSawCnt()}</span>
			</c:if>
			</li>

		</c:forEach>

	</ul><%@ include file="../layout/footer.jsp"%>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/js/chat/chat_list_view.js"></script>
</html>

