let index = {
		init:function(){
			$("#btn-save").on("click",()=>{	
				console.log("js : save()");
				this.save();
			});
			
			$("#btn-update").on("click",()=>{	
				console.log("js : update()");
				this.update();
			});
		
		},
		save:function(){
			
			let data={
					username: $('#username').val(),
					userid: $('#userid').val(),
					password: $('#password').val(),
					email: $('#email').val()
			}
			
			console.log(data);
			// ajax호출
			$.ajax({				
				type:"POST",
				url:"/auth/joinProc",
				data:JSON.stringify(data), // bodyData
				contentType:"application/json; charset=utf-8",
				dataType:"json" // 응답을 jsObject로 반환
			}).done(function(res){
				
				console.log(JSON.stringify(res));
				if(res.status == 200){
					alert("회원가입이 완료되었습니다.");
					location.href = "/home";
				}else{
					alert("회원가입에 실패하였습니다.");
				}		
			}).fail(function(err) {
				console.log("error: " + JSON.stringify(err));
			});
		},
		update:function(){

			let data={
					username: $('#username').val(),
					password: $('#password').val(),
					email: $('#email').val()
			}
			console.log(data);
			// ajax호출
			$.ajax({				
				type:"POST",
				url:"/user/updateInfo",
				data:JSON.stringify(data), // bodyData
				contentType:"application/json; charset=utf-8",
				dataType:"json" // 응답을 jsObject로 반환
			}).done(function(res){
				console.log(JSON.stringify(res));
				if(res.status == 200){
					alert("수정이 완료되었습니다.");
					location.href = "/home";
				}else{
					alert("수정에 실패하였습니다.");
					console.log(JSON.stringify(err));
				}		
			}).fail(function(err) {
				console.log("error: " + JSON.stringify(err));
			});
		}
}

index.init();