<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="post_read container">
	<div class="user_info">
		<div class="img" onclick="location.href='/profile/profileMain/'">
			<img src=/upload/${board.user.picture}>
		</div>
		
		<div class="txt">
			<div class="user_id">${board.user.nickname}</div>
			
			<div class="title">${board.title}</div>
			
			<div class="update_delete">
				<c:choose>
					<c:when test = "${user.userno == board.user.userno}">
						<button class="btn btn-primary" onclick="location.href='/post/post_update/${board.id}'">수정</button>
						<button id="btn_delete" class="btn btn-primary">삭제</button>
      			   </c:when>
      			   
      			   <c:otherwise>
      			   </c:otherwise>
				</c:choose>
			</div>
		</div>
		<input type="hidden" id="id" value="${board.id}">
	</div>
	
	<div class="content">
		${board.content}
	</div>
</div>
 
<script src="/js/post/write.js"></script>

<%@ include file="../layout/footer.jsp"%>
</body>
</html>