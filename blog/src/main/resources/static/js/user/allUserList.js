(function() {
	
	
	$(".btn-add-friend").click(function() {
		
		$.ajax({
			url: "/friend/add_friend",
			type: "POST",
			data : {
				friendno : $(this).val()
			},
			success: function(res) {
				console.log(res);
				alert("친구 추가 응답 ");
			}
		})
	});
	
	
	
})()
