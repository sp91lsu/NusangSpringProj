<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>
<br>
<br>
<div class="container">

	<form action="/manager/notice/noticeUpdateOk" method="post">
	<sec:csrfInput />
		<div class="form-group">
			<input type="hidden" name="no" value="${update.no }"> <input name="title" type="text" class="form-control" placeholder="Enter title" id="title" value="${update.title }">
		</div>
		<div class="form-group">
			<textarea name="contents" class="summernote" id="content">${update.contents }</textarea>
		</div>
		<div align = "center">
		<button type="submit" id="btn-save" class="btn btn-primary">수정 완료</button>
		<input type="button" class="btn btn-secondary" onclick="history.back()" value="취소"></input>
		</div>
	</form>
</div>

<script>
	$('.summernote').summernote({
		placeholder : "",
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
<%@ include file="../../layout/footer.jsp"%>
</body>
</html>


