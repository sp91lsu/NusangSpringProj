<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="" method="post">
		<div class="form-group">
			<input  name="title" type="text" class="form-control" placeholder="제목을 입력하세요" id="title">
		</div>
		<div class="form-group">
			<textarea   class="summernote" id="content"></textarea>
		</div>
	</form>
	<button id="btn_write" class="btn btn-primary">글쓰기 완료</button>
</div>
	
<%@ include file="../layout/footer.jsp"%>
</body>
</html>

<script>
	$('.summernote').summernote({
		placeholder : '내용을 입력하세요.',
		tabsize : 2,
		height : 300
	});
</script>
<script src="/js/post/write.js"></script>