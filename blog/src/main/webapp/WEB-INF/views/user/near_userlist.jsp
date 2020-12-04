<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container display-4 mt-5 mb-5">

	<h1>근처 회원</h1>
	
	<br>
	<c:forEach var="user_i" items="${nearUserList}">
		<div class="watchBox mr-5" style="float: left; ">
			<div class="watch">

				<img id="card_img" width="150px" src="${user_i.picture }" class="rounded-bottom">
				<div class="textBox" style="font-family: 'Noto Sans KR', sans-serif; margin-left: 5px;">
					<h5 class="productname" style="margin-top: 10px;">${user_i.nickname }
						<c:if test="${user_i.isMe(user_i) }">
			      나
			</c:if>
					</h5>
				</div>
				<button class="move btn btn-warning">프로필 보러가기</button>
			</div>
		</div>
	</c:forEach>

</div>
<br />

<%@ include file="../layout/footer.jsp"%>
</body>

<script type="text/javascript" src="/js/user/allUserList.js">
	
</script>
</html>


