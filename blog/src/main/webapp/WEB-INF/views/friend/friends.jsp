<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<h1>친구관리</h1>
	<div>
		<div class="row">
			<div class="col-12">
				<h2>내 친구 목록</h2>
				<ul class="list-group list-group-horizontal">
					<c:forEach var="friend" items="${user.friendList}">
						<c:if test="${friend.friendType == 'REALATIONSHIP' }">
							<li class="list-group-item">
								<form action="/chat/go_chatroom">
									<div>${friend.user.nickname }
										<button name="chat_userno" class="btn-chat btn btn-primary" value="${friend.user.userno }">${friend.user.userno }채팅하기</button>
									</div>
								</form>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	
	
	<div>
		내 신청 리스트
		<c:forEach var="friend" items="${user.friend_reqList(true)}">
			<div>${friend.user.nickname }</div>
		</c:forEach>
	</div>

	<div>
		나에게 온 요청리스트
		<c:forEach var="friend" items="${user.friend_reqList(false)}">
			<div>${friend.user.nickname }
				<button class="add_friend_btn btn btn-primary" value="${friend.user.userno }">${friend.user.userno }친구추가</button>
			</div>
		</c:forEach>
	</div>
</div>
<br />

<%@ include file="../layout/footer.jsp"%>
</body>

<script type="text/javascript" src="/js/friend/friend.js">
	
</script>
</html>
