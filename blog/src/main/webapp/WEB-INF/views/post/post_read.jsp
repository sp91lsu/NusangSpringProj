<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="post_read container" style="min-height: 200px;">
	<div class="post_title">${board.title}</div>

	<div class="user_info">
		<div class="img" onclick="location.href='/profile/profileMain/${board.user.userno}'">
			<img src="${board.user.picture}">
		</div>

		<div class="txt">
			<div class="txt_up" onclick="location.href='/profile/profileMain/${board.user.userno}'">
				${board.user.nickname}
			</div>
			
			<div class="txt_down">
				${board.createDate}
			</div>
		</div>
		
		<div class="update_delete">
			<c:choose>
				<c:when test="${user.userno == board.user.userno}">
					<button class="btn btn-primary"
						onclick="location.href='/post/post_update/${board.id}'">수정</button>
					<button id="btn_delete" class="btn btn-primary">삭제</button>
				</c:when>

				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</div>
		
		<input type="hidden" id="board_no" value="${board.id}"> 
		<input type="hidden" id="user_no" value="${user.userno}">
	</div>

	<hr>
	<div class="content">${board.content}</div>
	
	<input type="hidden" id="isWatchPost" value="${ board.isWishBoard(user)}"> 
	<input type="hidden" id="watchno" value="${board.wishList.size() }"> 
	<i id="heart_icon" style="cursor: pointer; width: 50px; height: 50px; margin: 5px 0 0 5px;"></i>
	
	<%@ include file="reply/reply_read.jsp"%>
</div>


<%@ include file="../layout/footer.jsp"%>

<script src="/js/post/post.js"></script>
<script src="/js/post/reply.js"></script>
<script src="/js/post/wish.js"></script>
</body>
</html>