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
				<fmt:formatDate value="${board.createDate}" pattern="yyyy.MM.dd '&nbsp;'HH:mm"/>
			</div>
			
			<div class="txt_down2">
				조회수${board.viewcnt}
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
	<!--글 내용-->
	<div class="content">${board.content}</div>
	
	<!--좋아요-->
	<i class="heart_icon far fa-heart"></i>
	<button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="padding: 0;">${board.wishList.size()} 명이 좋아합니다</button>
	<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		<c:forEach var="wishList" items="${board.wishList}">
			<li class="wish" onclick="location.href='/profile/profileMain/${wishList.me.userno}'">
				<img src="${wishList.me.picture}">
				<input type="hidden" value="${wishList.me.userno}">
				${wishList.me.nickname }
			</li>
		</c:forEach>
	</div>
	<input type="hidden" class="isWatchPost" value="${board.isWishBoard(user)}">
	<!--댓글-->
	<%@ include file="reply/reply_read.jsp"%>
</div>


<%@ include file="../layout/footer.jsp"%>

<script src="/js/post/post.js"></script>
<link rel="stylesheet" href="/css/post/post_read.css" />
<script src="/js/post/reply.js"></script>
<script src="/js/post/wish.js"></script>
</body>
</html>