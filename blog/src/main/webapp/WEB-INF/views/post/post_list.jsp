<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="btn_write">
	<button class="btn btn-primary" onclick="location.href='/post/post_write'">글쓰기</button>
</div>

<div class="post_list container">
	<c:forEach var="board" items="${boards.content}">
		<div class="post" onclick="location.href='/post/post_read/${board.id}'">
			<div class="img">
				<img src="/upload/${board.user.picture}" alt="" />
			</div>

			<div class="txt">
				<div class="writer">${board.user.nickname}</div>
				<div class="comment">${board.content}</div>
				<div class="view">
					<span>추천수0</span><span>조회수:0</span>
				</div>
			</div>
		</div>
	</c:forEach>
</div>