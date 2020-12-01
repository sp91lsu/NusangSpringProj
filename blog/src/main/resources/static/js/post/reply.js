var boardId;
var userno;
var reply_id;
var reply_content;
var detach_location;
var parent_location;
var update_comment;

var reply = {
	init : function() {
		$("#btn_reply_write").click(function() {// 댓글쓰기
			reply_content = $("#reply_content").val().trim();
			boardId = $("#board_no").val();
			userno = $("#user_no").val();

			if (reply_content == "") {
				alert("댓글을 입력해주세요");
			} else {
				reply.write();
			}
		}),

		$(".btn_reply_update").click(function() {// 수정하기
					reply_id = $(this).closest(".reply").children(".reply_id").val();
					reply_content = $(this).closest(".txt").children(".mid").text().trim();
					
					parent_location = detach_location = $(this).closest(".reply");
					detach_location = parent_location.children(".txt");
					
					detach_location.detach();
					parent_location.append("<div class='update_comment'>" +
												"<textarea class='update_reply'rows='3' cols='100'>" +"</textarea>"+
											"</div>"+
											
											"<div class='btn_uc'>" + 
												"<button class='btn_update_ok'>수정</button>" + 
												"<button class='btn_updatecancle'>취소</button>" + 
											"</div>");
					reply.init();
					$(".update_reply").text(reply_content);
					
					$(".btn_update_ok").click(function(){//수정완료
						update_comment = $(".update_reply").val();
						reply.update_reply();
						location.reload();
					}),
					
					$(".btn_updatecancle").click(function(){//수정취소
						$(".update_comment").remove();
						$(".btn_uc").remove();
						parent_location.append(detach_location);
					})
				}),
				
		$(".btn_reply_delete").click(function() {// 삭제하기
			reply_id = $(this).closest(".reply").children(".reply_id").val();
			
			reply.delete_reply();
		})
		
	},

	write : function() {// 댓글쓰기
		$.ajax({
			type : "POST",
			url : "/api/reply/write",
			headers : headers,
			data : {
				"userId" : userno,
				"boardId" : boardId,
				"content" : reply_content
			}
		}).done(function(res) {
			if (res == 1) {
				console.log("댓글쓰기 성공");
				location.reload();
			} else {
				console.log("댓글쓰기 실패");
			}
		}).fail(function(err) {
			console.log("error: " + err);
		})
	},

	delete_reply : function() {// 댓글삭제
		console.log("삭제할 댓글번호:" + reply_id);
		$.ajax({
			type : "POST",
			url : "/api/reply/delete",
			headers : headers,
			data : {
				"reply_id" : reply_id
			}
		}).done(function(res) {
			if (res == 1) {
				console.log("댓글삭제 성공");
				location.reload();
			} else {
				console.log("댓글삭제 실패");
			}
		}).fail(function(err) {
			console.log("error: " + err);
		})
	},

	update_reply : function() {
		$.ajax({
			type : "POST",
			url : "/api/reply/update",
			headers : headers,
			data : {
				"update_comment" : update_comment,
				"reply_id" : reply_id
			}
		}).done(function(res) {
			if (res == 1) {
				console.log("수정 성공");
			} else {
				console.log("수정 실패");
			}
		}).fail(function(err) {
			console.log("error: " + err);
		})
	}
}

reply.init();