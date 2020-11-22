<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<c:forEach var="users" items="${allUserList}">

		<div>${users.nickname }

			<c:if test="${user.availableReqFriend(users) }">
				<button class="btn-add-friend btn btn-primary" value="${users.userno }">${users.userno }친구요청</button>
			</c:if>
		</div>

	</c:forEach>

</div>
<br />

<%@ include file="../layout/footer.jsp"%>
</body>

<script type="text/javascript" src="/js/user/allUserList.js">
	
</script>
</html>


