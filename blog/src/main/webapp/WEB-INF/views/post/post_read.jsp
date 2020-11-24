<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="post_read container">
	<div class="user_info">
		<div class="img">
			<img src=/image/panda.jpg>
		</div>
		
		<div class="txt">
			<div class="user_id">아이디</div>
			
			<div class="update_delete">
				<button class="btn btn-primary">수정</button>
				<button id="btn_delete" class="btn btn-primary">삭제</button>
			</div>
		</div>
	</div>
	
	<div class="content">
		${board.content}
	</div>
</div>
<!--  
<script src="/js/post/write.js"></script>
-->

<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>
</body>
</html>