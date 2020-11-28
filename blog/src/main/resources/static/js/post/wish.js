(function() {

	var sendUrl;
	var toggleWatch;
	console.log($("#isWatchPost").val());
	function toggleWatchList(isWatchlist) {
		if (isWatchlist == true) {
			console.log("찜하기상태")
			sendUrl = "post/delete_watchlist"
			$("#heart_icon").attr('class', 'fas fa-heart');
			$("#heart_icon").css("color", "red");
			toggleWatch = true;
		} else {
			console.log("찜하기X")
			sendUrl = "post/set_watchlist"
			$("#heart_icon").attr('class', 'far fa-heart');
			$("#heart_icon").css("color", "black");
			toggleWatch = false;
		}
	}

	toggleWatchList($("#isWatchPost").val() == "true");
	var watchCnt = Number($("#watchno").val());
	$("#heart_icon").click(function() {

		$.ajax({

			url : sendUrl,
			type : "POST",
			data : {
				postno : $("#postno").val()
			},
			hearders : headers,
			success : function(data) {

				var jData = JSON.parse(data);
				console.log(data);

				if (jData.result > 0) {
					if (toggleWatch == true) {
						toggleWatchList(false);
						watchCnt -= 1;
					} else {
						alert("관심목록에 추가되었습니다.");
						watchCnt += 1;
						toggleWatchList(true);
					}
					$("#post_interest").text("관심 [" + watchCnt + "]");
				} else {
					alert("관심목록 수정 실패.");
				}

			}

		})

	})

})()
