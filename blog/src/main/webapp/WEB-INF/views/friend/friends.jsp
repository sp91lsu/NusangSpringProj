<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container display-4 mt-5 mb-5">

	<h1>친구</h1>





	<div class="dropdown">
		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">내 친구 목록 [${user.myFriendList().size()}]</button>
		<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			<c:forEach var="friend" items="${user.friendList}">
				<c:if test="${friend.friendType == 'REALATIONSHIP' }">
					<li class="list-group-item dropdown-item">
						<form action="/chat/go_chatroom">
						<img style="width: 50px; border-radius: 20px;" src="${friend.user.picture }" alt="" />
							<div>${friend.user.nickname }
								
								<button type="button"  class="btn-chat btn btn-warning" onclick="location.href='/profile/profileMain/${friend.user.userno}'">프로필</button>
								<button name="chat_userno" class="btn-chat btn btn-primary" value="${friend.user.userno }">채팅하기</button>
								<button type="button" class="delete_friend_btn btn btn-danger" value="${friend.user.userno }">삭제</button>
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
				<img style="width: 50px; border-radius: 20px;" src="${friend.user.picture }" alt="" />
				<button type="button"  class="btn-chat btn btn-warning" onclick="location.href='/profile/profileMain/${friend.user.userno}'">프로필</button>
					<div>${friend.user.nickname } <button type="button" class="cancel_friend_btn btn btn-danger" value="${friend.user.userno }">신청 취소</button></div>
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
					<img style="width: 50px; border-radius: 20px;" src="${friend.user.picture }" alt="" />
						<div>${friend.user.nickname }
						<button type="button"  class="btn-chat btn btn-warning" onclick="location.href='/profile/profileMain/${friend.user.userno}'">프로필</button>
							<button class="add_friend_btn btn btn-primary" value="${friend.user.userno }">친구추가</button>
							<button class="nagative_friend_btn btn btn-danger" value="${friend.user.userno }">거절</button>
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
