var title;
var content;

var post = {
		init:function(){
			$("#btn_write").click(function(){ //글쓰기
				title = $('#title').val().trim();
				content = $('#content').val();
				
				content = content.replace(/<p>/g , '');//치환(무시)할 문자들
				content = content.replace(/&nbsp;/g , '');
				content = content.replace(/<\/p>/g , '');
				content = content.replace(/<br>/g , '');
				content = content.trim();
				
				if(title==""){//제목공백
					alert("제목을 입력하세요");
				}else if(content==""){
					alert("내용을 입력하세요");
				}else {
					post.write();
				}
			});
			
			$("#btn_delete").click(function(){ //글삭제
				this.delete();
			});
		},
		
		write:function(){// 글쓰기처리
						
			$.ajax({	
				type:"POST",
				url:"/api/post",
				headers: headers,
				data:{
					"title" : title,// 제목
					"content" : content,// 내용
					
				}
			}).done(function(res){
				if(res == 1){
					alert("글쓰기 완료.");
					location.href = "/home";
				}else{
					alert("글쓰기 실패.");
				}		
			}).fail(function(err) {
				console.log("error: " + err);
			});
		},
		
		delete:function() { // 글 삭제처리
			var id = $("#id").val();
			
			$.ajax({
				type:"DELETE",
				url:"/api/post/" + id
			}).done(function(res){
				if(res == 1) {
					alert(id+"번글 삭제 성공");
					location.href = "/home";
				} else {
					alert("글삭제 실패");
				}
			}).fail(function(err) {
				console.log("error: " + err);
			});
		}
}

post.init();