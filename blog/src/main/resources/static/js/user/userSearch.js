
(function() {
	
	$("#btn-userSearch").click(function(){	
			$.ajax({
				
				url:"/user/search_ok",
				type: "POST",
				data: {
					searchValue : $("#searchValue").val()
				},
				success: function(res) {
					
					console.log(res)
				}
				
				
			})
	}
	)
	
	
	
	
})()