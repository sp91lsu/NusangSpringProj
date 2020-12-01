<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="reply container" style="min-height: 200px;">
	<div class="reply_cutline">
		댓글 ${board.replyList.size()}
	</div>
	
	<div class="reply_list">
		<c:forEach var="reply" items="${board.replyList}">
			<div class="reply">
				<hr>
				<input type="hidden" class="reply_id" value="${reply.id}">
				<div class="img" onclick="location.href='/profile/profileMain/${reply.user.userno}'">
					<img src="${reply.user.picture}">
				</div>
				
				<div class="txt">
					<div class="top">
						<div class="nickname" onclick="location.href='/profile/profileMain/${reply.user.userno}'">
							${reply.user.nickname}
						</div>
						
						<c:choose>
							<c:when test = "${board.user.userno == reply.user.userno}">
							<div class="myReply">
								&nbsp;작성자&nbsp;
							</div>
							</c:when>
						</c:choose>
						
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
		<div class="write_replyContent">
			<textarea id="reply_content" rows="3" cols="100" placeholder="댓글을 입력하세요"></textarea>
		</div>
		
		<div class="btn_WriteReply">
			<input type='checkbox' id='secretmode' >비밀댓글
			<button id="btn_reply_write">등록</button>
		</div>
	</div>
	
</div>
