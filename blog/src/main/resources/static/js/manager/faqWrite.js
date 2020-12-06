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