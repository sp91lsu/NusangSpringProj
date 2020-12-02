<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
				<fmt:formatDate value="${board.createDate}" pattern="yyyy.MM.dd '&nbsp;'HH:mm"/>
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
	<i class="heart_icon far fa-heart"></i> ${board.wishList.size()}
	<input type="hidden" class="isWatchPost" value="${board.isWishBoard(user)}">
	
	<%@ include file="reply/reply_read.jsp"%>
</div>


<%@ include file="../layout/footer.jsp"%>

<script src="/js/post/post.js"></script>
<script src="/js/post/reply.js"></script>
<script src="/js/post/wish.js"></script>
</body>
</html>