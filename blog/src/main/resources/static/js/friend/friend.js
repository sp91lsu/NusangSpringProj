(function() {

	
	
	
	
	$(".cancel_friend_btn").click(function() {
		$.ajax({
			url : "/friend/delete_friend",
			type : "POST",
			data : {
				friendno : $(this).val()
			},
			headers:headers,
			success : function(res) {
				console.log(res);
				if (res == 1) {
					alert("신청 취소 완료.");
					location.reload();
				}else{
					alert("신청 취소 실패.");
				}
			}
		})
	});
	
	$(".nagative_friend_btn").click(function() {
		$.ajax({
			url : "/friend/nagative_friend",
			type : "POST",
			data : {
				friendno : $(this).val()
			},
			headers:headers,
			success : function(res) {
				console.log(res);
				if (res == 1) {
					alert("요청을 거절하였습니다.");
					location.reload();
				}else{
					alert("요청을 거절 실패.");
				}
			}
		})
	});
	
	$(".delete_friend_btn").click(function() {
		$.ajax({
			url : "/friend/delete_friend",
			type : "POST",
			data : {
				friendno : $(this).val()
			},
			headers:headers,
			success : function(res) {
				console.log(res);
				if (res == 1) {
					alert("친구 삭제 완료.");
					location.reload();
				}else{
					alert("친구 삭제 실패.");
				}
			}
		})
	});
	
	$(".add_friend_btn").click(function() {
		$.ajax({
			url : "/friend/add_friend",
			type : "POST",
			data : {
				friendno : $(this).val()
			},
			headers:headers,
			success : function(res) {
				console.log(res);
				if (res == 1) {
					alert("친구 추가 완료.");
					location.reload();
				}else{
					alert("친구 추가 실패.");
				}
			}
		})
	});

})()
