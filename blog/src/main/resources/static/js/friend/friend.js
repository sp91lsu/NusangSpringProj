(function() {

	
	
	$(".add_friend_btn").click(function() {
		$.ajax({
			url : "/friend/add_friend",
			type : "POST",
			data : {
				friendno : $(this).val()
			},
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
