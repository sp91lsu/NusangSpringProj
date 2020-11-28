<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="post_read container">
	<div class="user_info">
		<div class="img" onclick="location.href='/profile/profileMain/${board.user.userno}'">
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
		<input type="hidden" id="board_no" value="${board.id}">
		<input type="hidden" id="user_no" value="${user.userno}">
	</div>
	
	<div class="content">
		${board.content}
	</div>
</div>

<%@ include file="reply/reply_read.jsp"%>
  
<%@ include file="../layout/footer.jsp"%>

<script src="/js/post/post.js"></script>
<script src="/js/post/reply.js"></script>
</body>
</html>