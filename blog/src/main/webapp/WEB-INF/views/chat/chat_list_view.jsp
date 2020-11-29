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

	<h2>채팅 리스트</h2>
	<ul class="list-group">

		<c:forEach var="chatRoom" items="${chatRoomList }">
			<li onclick="location.href='/chat/chatpage?chat_userno=${chatRoom.getMatchedUser().userno}'" class="list-group-item d-flex justify-content-between align-items-center">${chatRoom.getMatchedUserName() } <span class="badge badge-primary badge-pill">14</span>
			</li>

		</c:forEach>

	</ul><%@ include file="../layout/footer.jsp"%>
</body>
</html>

