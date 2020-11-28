<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="reply container" style="min-height: 200px;">
	<div class="reply_list">
		<div class="reply">
			<div class="img">
				<img src="/image/panda.jpg">
			</div>
			
			<div class="txt">
				<div class="top">
					<div class="nickname">닉네임</div>
					<div class="btn_ud">
						<button>수정</button>
						<button>삭제</button>
					</div>
				</div>
				
				<div class="mid">
					여기는 내용
				</div>
				
				<div class="bot">
					2020.11.27  20:24
				</div>
			</div>
		</div>
	</div>
	
	<div class="reply_write">
		<textarea id="reply_content" rows="3" cols="50" placeholder="댓글을 입력하세요"></textarea>
		<input type="button" id="btn_reply_write" value="등록">
	</div>
	
</div>
