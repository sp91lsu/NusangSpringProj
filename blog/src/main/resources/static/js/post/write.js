
let post = {
		
		init:function(){
			
			$("#btn_write").on("click",()=>{
				this.write();
			});
			
			$("#btn_delete").on("click",()=>{	
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
				},
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
		delete:function(){
			console.log("삭제버튼 클릭!");
		}
}

post.init();