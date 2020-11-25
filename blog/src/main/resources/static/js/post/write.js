
var post = {
		init:function(){
			$("#btn_write").on("click",()=>{ //글쓰기
				console.log($('#content').val().trim());
				if(($('#title').val().trim()=="")){//제목공백
					alert("제목");
				}else if($('#content').val().trim()==""){
					alert("내용");
				}else {
					this.write();
				}
			});
			
			$("#btn_delete").on("click",()=>{//글삭제
				this.delete();
			});
		},
		
		write:function(){// 글쓰기
			console.log("글쓰기중 내용:"+$('#content').val());
						
			$.ajax({	
				type:"POST",
				url:"/api/post",
				data:{
					"title" : $('#title').val(),// 제목
					"content" : $('#content').val(),// 내용
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
		
		delete:function() { // 글 삭제
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