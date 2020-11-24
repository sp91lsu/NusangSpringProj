
let index = {
		
		init:function(){
			$("#btn_save").on("click",()=>{	
				this.save();
			});
			
			$("#btn_delete").on("click",()=>{
				this.deleteById();
			});
			
			$("#btn-update").on("click",()=>{	
				this.update();
			});
			$("#btn-reply-save").on("click",()=>{	
				this.replySave();
			});
		},
		
		save:function(){

			console.log("js : save()");
			console.log($('#content').val());
			let data={
					title: $('#title').val(),
					content: $('#content').val(),
			}
			
			// ajax호출
			$.ajax({				
				type:"POST",
				url:"/api/board",
				data:JSON.stringify(data), // bodyData
				contentType:"application/json; charset=utf-8",
				dataType:"json" // 응답을 jsObject로 반환
			}).done(function(res){
				
				console.log(JSON.stringify(res));
				if(res.status == 200){
					alert("글쓰기가 완료되었습니다.");
					location.href = "/home";
				}else{
					alert("글쓰기가 실패하였습니다.");
				}		
			}).fail(function(err) {
				console.log("error: " + JSON.stringify(err));
			});
		},
		deleteById: function() {
			
			let id = $("#id").text();
			console.log(id);
			
			// ajax호출
			$.ajax({				
				type:"DELETE",
				url:"/api/board/" + id,
				contentType:"application/json; charset=utf-8",
				dataType:"json" // 응답을 jsObject로 반환
			}).done(function(res){
				console.log(JSON.stringify(res));
				if(res.status == 200){
					alert("삭제가 완료되었습니다.");
					location.href = "/home";
				}else{
					alert("삭제에 실패하였습니다.");
				}		
			}).fail(function(err) {
				console.log("error: " + JSON.stringify(err));
			});
		},
		update:function(){

			
			let data={
					title: $('#title').val(),
					content: $('#content').val(),
					id : $("#id").val()
			}
			
			// ajax호출
			$.ajax({				
				type:"PUT",
				url:"/api/board/update",
				contentType:"application/json; charset=utf-8",
				data: JSON.stringify(data),
				dataType:"json" // 응답을 jsObject로 반환
			}).done(function(res){
				
				if(res.status == 200){
					alert("글수정이 완료되었습니다.");
					location.href = "/home";
				}else{
					alert("글수정에 실패하였습니다." );
					console.log(res);
				}		
			}).fail(function(err) {
				console.log("error: " + JSON.stringify(err));
			});
		},
		replySave:function(){

			let boardId  =$('#boardId').val();
			
			let data={
					userId: $('#userId').val(),
					boardId: boardId,
					content: $('#reply-content').val(),
			}
			
			console.log(data);
			// ajax호출
			$.ajax({				
				type:"POST",
				url: "/api/board/replysave",
				data:JSON.stringify(data), // bodyData
				contentType:"application/json; charset=utf-8",
				dataType:"json" // 응답을 jsObject로 반환
			}).done(function(res){
				
				console.log(JSON.stringify(res));
				if(res.status == 200){
					alert("댓글작성이 완료되었습니다.");
					location.href = "/board/"+ boardId;
				}else{
					alert("댓글작성에 실패하였습니다.");
				}		
			}).fail(function(err) {
				console.log("error: " + JSON.stringify(err));
			});
		},
		deleteReply: function(replyId) {
			
			let boardId  =$('#boardId').val();
			
			// ajax호출
			$.ajax({				
				type:"DELETE",
				url:"/api/board/reply/" + replyId,
				contentType:"application/json; charset=utf-8",
				dataType:"json" // 응답을 jsObject로 반환
			}).done(function(res){
				
				console.log(JSON.stringify(res));
				if(res.status == 200){
					alert("댓글삭제가 완료되었습니다.");
					location.href = "/board/"+ boardId;
				}else{
					alert("댓글삭제에 실패하였습니다.");
				}		
			}).fail(function(err) {
				console.log("error: " + JSON.stringify(err));
			});
		}
		
}

index.init();