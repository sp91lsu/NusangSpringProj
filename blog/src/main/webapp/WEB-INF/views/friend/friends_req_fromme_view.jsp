<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<c:forEach var="friend" items="${user.friend_reqList(true)}">
		<form action="/chat/go_chatroom">
			<div>${friend.user.nickname }
				<button name="chat_userno" class="btn-chat btn btn-primary" value="${friend.user.userno }">${friend.user.userno }친구추가</button>
			</div>
		</form>
	</c:forEach>

</div>
<br />

<%@ include file="../layout/footer.jsp"%>
</body>

<script type="text/javascript" src="/js/user/allUserList.js">
	
</script>
</html>


