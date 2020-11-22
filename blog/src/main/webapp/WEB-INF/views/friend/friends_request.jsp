<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<c:forEach var="friend" items="${user.friend_reqList(false)}">
		<div>${friend.user.nickname }
			<button class="add_friend_btn btn btn-primary" value="${friend.user.userno }">${friend.user.userno }친구추가</button>
		</div>
	</c:forEach>

</div>
<br />

<%@ include file="../layout/footer.jsp"%>
</body>

<script type="text/javascript" src="/js/friend/friend.js">
	
</script>
</html>


