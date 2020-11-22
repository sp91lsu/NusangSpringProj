(function() {

	$(".btn-add-friend").click(function() {
		$.ajax({
			url : "/friend/add_friend_req",
			type : "POST",
			data : {
				friendno : $(this).val()
			},
			success : function(res) {
				console.log(res);
				if (res == 1) {
					alert("친구 요청에 성공하였습니다.");
					location.reload();
				}else{
					alert("친구 요청실패.");
				}
			}
		})
	});

})()
