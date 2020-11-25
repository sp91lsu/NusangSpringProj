<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="manager_header.jsp"%>
<br>
<br>
<div class="container">

	<form name = "writeform" action="/manager/noticeWriteOk" method="post">
		<div class="form-group">
			<input  name="title" type="text" class="form-control" placeholder="Enter title" id="title">
		</div>
		<div class="form-group">
			<textarea name = "contents" class="summernote" id="contents"></textarea>
		</div>
		<button type = "button" id="btn-save" class="btn btn-primary">글쓰기 완료</button>
	</form>
</div>

<script>
	$('.summernote').summernote({
		placeholder : 'Hello stand alone ui',
		tabsize : 2,
		height : 300
	});
	
	$("#btn-save").click(function(){
		var title = $("#title").val().trim();
		var contents = $("#contents").val();
		contents = contents.replace(/<p>/g,'');
		contents = contents.replace(/<\/p>/g,'');
		contents = contents.replace(/<br>/g,'');
		contents = contents.replace(/&nbsp;/g,'');
		contents = contents.trim(); 
		//alert("내용 : " + contents);
		if((title == "") || (contents == "")){
			alert("제목과 내용을 모두 작성해주세요");
		}else{
			${"writeform"}.submit();
		}
	})
</script>
<%@ include file="manager_footer.jsp"%>
</body>
</html>


