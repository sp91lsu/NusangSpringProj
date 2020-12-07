(function() {

	var sendUrl;
	console.log($(".isWatchPost").val());
	function toggleWatchList(isWatchlist,element) {
		if (isWatchlist == true) {
			console.log("찜하기상태")
			sendUrl = "/api/post/delete_wish"
			$(element).find(".heart_icon").attr('class', 'heart_icon fas fa-heart');
			$(element).find(".heart_icon").css("color", "red");
		} else {
			console.log("찜하기X")
			sendUrl = "/api/post/add_wish"
			$(element).find(".heart_icon").attr('class', 'heart_icon far fa-heart');
			$(element).find(".heart_icon").css("color", "black");
		}
	}
	
	  $(".post").each(function(index, item){
		  
		 console.log(index)
		  toggleWatchList( $(item).find(".isWatchPost").val() == "true",this);
	  })

		
	

})()
