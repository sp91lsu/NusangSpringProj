	$('#picUpdateBtn').click(function(){
		$('#file').click();
	})
	
	document.getElementById("file").onchange = function() {
		$('#loading').show();
		document.getElementById("form").submit();
		
	};
	
	// 닉네임 변경
 	$('#nicknameChange').click(function(){
		var nickName = $('#nickName').val();
		
		$.ajax({
			url : "/api/profile/nickNameUpdate" ,
			type : "POST",
			"headers" : headers,
			data : {
				"nickName" : nickName
			},
			success : function(res) {
				if (res == 0) {
					alert("닉네임이 변경되었습니다.");
				} else if(res == 1) {
					alert("닉네임 변경에 실패했습니다. 다시 시도해주세요.\n(2~8글자의 닉네임을 입력해 주세요.)");
				} else if(res == 2) {
					alert("중복된 닉네임 입니다. 다시 시도해주세요.\n(2~8글자의 닉네임을 입력해 주세요.)");
				}
				location.reload();
			}
		})
	}) 
	
	// 사진 삭제(기본 이미지로)
	$('#picDeleteBtn').click(function(){
		$.ajax({
			url : "/api/profile/deletePicture" ,
			type : "GET",
			success : function(res) {

				if (res == 0) {
					alert("사진 변경에 실패했습니다. 다시 시도해주세요.");
				} else {
					alert("기존 사진이 삭제되었습니다.")
				}
				location.reload();
			}
		})
	})
	
	//성별 변경
	function genderSelected(){
 		var gender = $('#genderSelect option:selected').val();
 		if(gender == "남"){
 			gender = "MALE"
 		}else if(gender == "여"){
 			gender = "FEMALE"
 		}
 		
 		$.ajax({
 			url : "/api/profile/genderSelect" ,
			type : "POST",
			"headers" : headers,
			data : {
				"gender" : gender
			},
			success : function(res) {

				if (res == 0) {
					alert("성별 변경에 실패했습니다. 다시 시도해주세요.");
				} else {
					alert("성별이 변경되었습니다.");
				}
				location.reload();
			}
 			
 		})
 	}
 	
 	// 나이 변경
 	function ageSelected(){
 		var age = $('#ageSelect option:selected').val();
 		
 		$.ajax({
 			url : "/api/profile/ageSelect" ,
			type : "POST",
			"headers" : headers,
			data : {
				"age" : age
			},
			success : function(res) {
				if (res == 0) {
					alert("나이 변경에 실패했습니다. 다시 시도해주세요.");
				} else {
					alert("나이가 변경되었습니다.");
				}
				location.reload();
			}
 		})
 	}
 	
 	
 	// 친구추가 요청
 	function friendReq(){
 		var friendno = $('#userno').val()
 		
 		$.ajax({
 			url: "/friend/add_friend_req",
 			type: "POST",
 			"headers" : headers,
 			data: {
 				"friendno" : friendno,
 			},
 			success : function(res) {
 				if(res == 1){
					alert("친구추가 요청을 완료했습니다.") 				
 				} else{
 					alert("친구추가 요청에 실패했습니다 \n 다시 시도해주세요") 				
 				}
 				location.reload();
 			}
 		
 		
 		})
 	
 	
 	}
 	
 	
	//페이징(무한 스크롤) 함수
 	var page = 0;
	var userno = $('#userno').val();

	$(window).scroll(function() {
	
		maxHeight = $(document).height();
		currentScroll = $(window).scrollTop() + $(window).height();
		
	   // if (maxHeight <= currentScroll) {
	    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
	      console.log('바로밑이 페이지 로그');
	      console.log(++page);
	      
	      $.ajax({
	    	 url : "/api/profile/updatePost?page="+page+"&userno="+userno,
				
		success : function(res) {
			console.log(res)
	 		res.content.forEach(element => {
	 			var postPath = "'/post/post_read/"+element.id+"'"
				var post =
				
				'<div class="post" onclick="location.href='+postPath+'">'+
					'<div class="img">'+
						'<img src="'+element.user.picture+'">'+
					'</div>'+

					'<div class="txt">'+
						'<div class="top">'+
							'<div class="writer">'+element.user.nickname+'</div>'+
							'<div class="post_title">'+element.title+'</div>'+
						'</div>'+
						'<div class="comment">'+element.content+'</div>'+
						'<div class="view">'+
							'<span><i class="heart_icon far fa-heart"></i> '+element.wishList.length+'</span>'+
							'<span>조회수'+element.viewcnt+'</span>'+
							'<span>댓글'+element.replyList.length+'</span>'+
						'</div>'+
					'</div>'+
				'</div>'
				
				$(".community_list").append(post)
				console.log(element)
				}); 
			}
	      })
	    }
	});
	
	
 	