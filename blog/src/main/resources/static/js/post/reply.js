var reply_content;
var boardId;
var userno;

var reply = {
	init : function() {
		$("#btn_reply_write").click(function() {
			reply_content = $("#reply_content").val().trim();
			boardId = $("#board_no").val();
			userno = $("#user_no").val();
			
			if (reply_content == "") {
				alert("댓글을 입력해주세요");
			} else {
				reply.write();
			}
		})
	},

	write : function() {
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
			} else {
				console.log("댓글쓰기 실패");
			}
		}).fail(function(err) {
			console.log("error: " + err);
		})
	}
}

reply.init();