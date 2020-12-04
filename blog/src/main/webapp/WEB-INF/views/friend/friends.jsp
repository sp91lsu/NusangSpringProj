<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container display-4 mt-5 mb-5">

	<h1>친구관리</h1>





	<div class="dropdown">
		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">내 친구 목록 [${user.myFriendList().size()}]</button>
		<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			<c:forEach var="friend" items="${user.friendList}">
				<c:if test="${friend.friendType == 'REALATIONSHIP' }">
					<li class="list-group-item dropdown-item">
						<form action="/chat/go_chatroom">
							<div>${friend.user.nickname }
								<button name="chat_userno" class="btn-chat btn btn-primary" value="${friend.user.userno }">채팅하기</button>
							</div>
						</form>
					</li>
				</c:if>
			</c:forEach>
		</div>
	</div>

	<div class="dropdown">
		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">내 신청 리스트[${user.friend_reqList(true).size()}]</button>
		<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			<c:forEach var="friend" items="${user.friend_reqList(true)}">
				<li class="list-group-item">
					<div>${friend.user.nickname }</div>
				</li>
			</c:forEach>
		</div>
	</div>

	<div class="dropdown">
		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">나에게 온 요청리스트[${user.friend_reqList(false).size()}]</button>
		<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			<c:forEach var="friend" items="${user.friend_reqList(false)}">
				<c:if test="${friend.friendType == 'REQUEST' }">
					<li class="list-group-item">
						<div>${friend.user.nickname }
							<button class="add_friend_btn btn btn-primary" value="${friend.user.userno }">${friend.user.userno }친구추가</button>
						</div>
					</li>
				</c:if>
			</c:forEach>
		</div>
	</div>






</div>
<br />

<%@ include file="../layout/footer.jsp"%>
</body>

<script type="text/javascript" src="/js/friend/friend.js">
	
</script>
</html>
