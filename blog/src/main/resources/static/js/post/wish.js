(function() {

	var sendUrl;
	var toggleWatch;
	console.log($(".isWatchPost").val());
	function toggleWatchList(isWatchlist) {
		if (isWatchlist == true) {
			console.log("찜하기상태")
			sendUrl = "/api/post/delete_wish"
			$(".heart_icon").attr('class', 'heart_icon fas fa-heart');
			$(".heart_icon").css("color", "red");
			toggleWatch = true;
		} else {
			console.log("찜하기X")
			sendUrl = "/api/post/add_wish"
			$(".heart_icon").attr('class', 'heart_icon far fa-heart');
			$(".heart_icon").css("color", "black");
			toggleWatch = false;
		}
	}
	
	toggleWatchList($(".isWatchPost").val() == "true");
	$(".heart_icon").click(function() {

		$.ajax({

			url : sendUrl,
			type : "POST",
			data : {
				boardno : $("#board_no").val()
			},
			headers : headers,
			success : function(data) {

				console.log(data);

				if (data > 0) {
					if (toggleWatch == true) {
						alert("관심목록에서 빠졌습니다.");
					} else {
						alert("관심목록에 추가되었습니다.");
					}
					location.reload();
				} else {
					alert("관심목록 수정 실패.");
				}

			}

		})

	})

})()
