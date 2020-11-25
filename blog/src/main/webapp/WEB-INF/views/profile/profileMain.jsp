<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/profile/profileMain.css" />


<div class="container">
	
	<div class="pictureSection">
		<img id="img" class="profileImg" src="/upload/${user.picture }">
		<form action="/profile/updatePicture" id="form" method="post" enctype="multipart/form-data">
			<input type="file" name="file" id="file" accept=".gif, .jpg, .png" style="display: none">
		</form>
		<button class="picUpdateBtn" id="picUpdateBtn">사진변경</button>
		<button class="picDeleteBtn" id="picDeleteBtn">기존사진 삭제</button>
	</div>
	<div class="infoSection">
		<div class="nicknameSection">
			<form>
				<input class="nickName" name="nickName" type="text" id="nickName" value=${user.nickname } >
				<button type="button" id="nicknameChange">닉네임 변경</button>
			</form>
		</div>
		<span>성별: ${user.gender } / 나이: ${user.age } 세</span>
		<ul>
			<li>좋아요글 3</li>
			<li>나의 캐쉬 40 coin</li>
		</ul>

	</div>
	<hr>
	<div class="postSection row">
		<div class="myPost col-7">나의 글 넣을곳</div>
		<div class="createPost col-4">
			<h4>나의 일상을 공유해 보세요</h4>
			<button>글쓰기</button>
		</div>
	</div>

</div>

<script>
	$('#picUpdateBtn').click(function(){
		$('#file').click();
	})
	
	document.getElementById("file").onchange = function() {
		document.getElementById("form").submit();
		
	};
	
 	$('#nicknameChange').click(function(){
		var nickName = $('#nickName').val();
		
		$.ajax({
			url : "/api/profile/nickNameUpdate" ,
			type : "POST",
			data : {
				"nickName" : nickName
			}
		})
	}) 
	
	
	
	
</script>

<%@ include file="../layout/footer.jsp"%>

