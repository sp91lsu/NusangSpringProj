
var post = {
		init:function(){
			
			$("#btn_write").on("click",()=>{ //글쓰기
				if(($('#title').val().trim()=="") || $('#content').val().trim==""){// 제목이나 내용 공백이면 글쓰기 불가
					alert("제목과 내용을 작성해주세요");
				} else {
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
			console.log(id + "번글 삭제중");
			
			$.ajax({
				type:"DELETE",
				url:"/api/post/" + id
			}).done(function(res){
				if(res == 1) {
					console.log("글삭제 성공");
				} else {
					console.log("글삭제 실패");
				}
			}).fail(function(err) {
				console.log("error: " + err);
			});
		}
}

post.init();