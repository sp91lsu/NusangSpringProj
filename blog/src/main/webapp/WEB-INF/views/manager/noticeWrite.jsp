<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="manager_header.jsp"%>
<br>
<br>
<div class="container">

	<form action="/manager/noticeWriteOk" method="post">
		<div class="form-group">
			<input  name="title" type="text" class="form-control" placeholder="Enter title" id="title">
		</div>
		<div class="form-group">
			<textarea name = "contents" class="summernote" id="content"></textarea>
		</div>
		<button type = "submit" id="btn-save" class="btn btn-primary">글쓰기 완료</button>
	</form>
</div>

<script>
	$('.summernote').summernote({
		placeholder : 'Hello stand alone ui',
		tabsize : 2,
		height : 300
	});
</script>
<%@ include file="manager_footer.jsp"%>
</body>
</html>


