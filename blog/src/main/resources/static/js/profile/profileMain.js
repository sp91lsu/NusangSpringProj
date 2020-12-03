	$('#picUpdateBtn').click(function(){
		$('#file').click();
	})
	
	document.getElementById("file").onchange = function() {
		$('#loading').show();
		document.getElementById("form").submit();
		
	};
	
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
	
 	var page = 0;
	var userno = $('#userno').val();

	$(window).scroll(function() {
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
						'<div class="writer">'+element.title+'</div>'+
						'<div class="comment">'+element.content+'</div>'+
						'<div class="view">'+
							'<span>추천수0</span><span>조회수:0</span>'+
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
 	