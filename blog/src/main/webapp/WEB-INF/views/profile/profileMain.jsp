<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/profile/profileMain.css" />


<div class="container">
	
	<img id="img" class="profileImg" src="/upload/${user.picture }">
	<form action="/profile/updatePicture" id="form" method="post" enctype="multipart/form-data">
		<input type="file" name="file" id="file" style="display: none">
	</form>
	<div class="infoSection">
		<div class="nicknameSection">
			<span class="nickname">닉네임넣을곳</span>
			<button>닉네임 변경</button>
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
	$('#img').click(function(){
		console.log("aaa")
		$('#file').click();
	})
	
	document.getElementById("file").onchange = function() {
		document.getElementById("form").submit();
		
	};
	
</script>

<%@ include file="../layout/footer.jsp"%>

