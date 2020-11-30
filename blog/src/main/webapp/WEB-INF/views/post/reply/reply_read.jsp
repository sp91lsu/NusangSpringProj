<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="reply container" style="min-height: 200px;">
	<div class="reply_list">
	
		<c:forEach var="reply" items="${board.replyList}">
			<div class="reply">
				<input type="hidden" class="reply_id" value="${reply.id}">
				<div class="img" onclick="location.href='/profile/profileMain/${reply.user.userno}'">
					<img src="${reply.user.picture}">
				</div>
				
				<div class="txt">
					<div class="top">
						<div class="nickname" onclick="location.href='/profile/profileMain/${reply.user.userno}'">
							${reply.user.nickname}
						</div>
						
						<div class="btn_ud">
						<c:choose>
							<c:when test = "${reply.user.userno == user.userno}">
								<button class="btn_reply_update">수정</button>
								<button class="btn_reply_delete">삭제</button>
							</c:when>
						</c:choose>
						</div>
					</div>
					
					<div class="mid">
						<div class="reply_content">
							${reply.content}
						</div>
					</div>
					
					<div class="bot">
						${reply.createDate}
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<div class="reply_write">
		<textarea id="reply_content" rows="3" cols="50" placeholder="댓글을 입력하세요"></textarea>
		<input type="button" id="btn_reply_write" value="등록">
	</div>
	
</div>
