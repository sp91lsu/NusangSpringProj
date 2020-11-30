var boardId;
var userno;
var reply_id
var reply_content;

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
			console.log("수정하기 버튼 클릭");
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
	}
}

reply.init();