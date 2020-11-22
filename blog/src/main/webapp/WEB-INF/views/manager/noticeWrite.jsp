<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="manager_header.jsp"%>
<br>
<br>
<div class="container">

	<form action="" method="post">
		<div class="form-group">
			<input  name="title" type="text" class="form-control" placeholder="Enter title" id="title">
		</div>
		<div class="form-group">
			<textarea   class="summernote" id="content"></textarea>
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">글쓰기 완료</button>
</div>

<script>
	$('.summernote').summernote({
		placeholder : 'Hello stand alone ui',
		tabsize : 2,
		height : 300
	});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
</body>
</html>


