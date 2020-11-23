<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<c:forEach var="user_i" items="${nearUserList}">
		<div>${user_i.nickname }

			<c:if test="${!user_i.isMe(user_i) }">
				<button class="btn-add-friend btn btn-primary" value="${user_i.userno }">${user_i.userno }친구요청</button>
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


