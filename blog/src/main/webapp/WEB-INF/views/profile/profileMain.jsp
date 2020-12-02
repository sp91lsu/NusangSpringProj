<%@page import="com.mycom.blog.controller.assist.ConAssist"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/profile/profileMain.css" />


<div class="container">

	<c:choose>
		<%-- 내가 아닌 user의 프로필 --%>
		<c:when test="${user.userno != boardUser.userno && !empty boardUser.userno }">
		<input type="hidden" id="userno" value="${boardUser.userno }">
			<%-- 프로필 사진 --%>
			<div class="pictureSection">
				<c:choose>
					<c:when test="${!empty boardUser.picture}">
						<img id="img" class="profileImg" src="${boardUser.picture }">
					</c:when>
					<c:otherwise>
						<img id="img" class="profileImg" src="/image/profileImg.jpg">
					</c:otherwise>
				</c:choose>
				<input type="hidden" id="file">
			</div>

			<%-- 닉네임 및 정보 --%>
			<div class="infoSection">
				<div class="nicknameSection">
					<form>
						<span class="nickName" id="nickName">${boardUser.nickname }</span>
					</form>
				</div>
				<span>성별: ${boardUser.gender.toString() }&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;나이: ${boardUser.age } 세</span>
				<ul>
					<li>좋아요글 3</li>
					<li>나의 캐쉬 40 coin</li>
				</ul>

				<div class="friendUser">
					<c:set var="chkFriend" value="${user.chkFriend(boardUser.userno)}"></c:set>
					<c:choose>
						<c:when test="${chkFriend eq 0 }">
							<button>친구추가 하기</button>
						</c:when>
						<c:when test="${chkFriend eq 1 }">
							<span>친구 요청중</span>
						</c:when>
						<c:when test="${chkFriend eq 2 }">
							<span>이미 친구임</span>
						</c:when>
					
					</c:choose>
				
				
				</div>



				<form id="go_chat_form" action="/chat/go_chatroom" method="get">
					<sec:csrfInput />
					<input type="hidden" name="chat_userno" value="${boardUser.userno  }"/>
					<c:if test="${!user.isMyFriend(boardUser)}">
						<c:choose>
							<c:when test="${user.whichOfMyGoodsUse() == 1  }">
								<button id="chatBtn">채팅하기(무료채팅)</button>
							</c:when>
							<c:when test="${user.whichOfMyGoodsUse() == 2  }">
								<button type="button" id="use-coin-btn">채팅하기(코인사용)</button>
							</c:when>
							<c:otherwise>
								<button type="button" id="buy-coin-btn">채팅하기(코인구매)</button>
							</c:otherwise>

						</c:choose>
					</c:if>
					<c:if test="${user.isMyFriend(boardUser)}">
						<button id="chatBtn">채팅하기(내친구)</button>
					</c:if>
				</form>
				<jsp:include page="../layout/modal.jsp" flush="true">
					<jsp:param name="modalId" value='<%=URLEncoder.encode("modal_use_coin", "UTF-8")%>' />
					<jsp:param name="title" value='<%=URLEncoder.encode("무료채팅 소진", "UTF-8")%>' />
					<jsp:param name="body" value='<%=URLEncoder.encode(ConAssist.useTalkCoin + "코인을 사용하여 채팅하시겠습니까? ", "UTF-8")%>' />
				</jsp:include>
				<jsp:include page="../layout/modal.jsp" flush="true">
					<jsp:param name="modalId" value='<%=URLEncoder.encode("modal_buy_coin", "UTF-8")%>' />
					<jsp:param name="title" value='<%=URLEncoder.encode("코인 소진", "UTF-8")%>' />
					<jsp:param name="body" value='<%=URLEncoder.encode("코인이 모두 소진되었습니다. 상점으로 이동하시겠습니까? ", "UTF-8")%>' />
				</jsp:include>

				<button class="modalBtn" class="btn">button</button>

				<script>
		$('#use-coin-btn').click(function(e) {
			
			let modal = $('#modal_use_coin');
			 
			$(modal).modal("show");
			
			let modalY = $('#modal_use_coin #modalY');
			console.log($(modalY).text())
			$(modalY).click(function(e) {
				
				$(modal).modal('hide').data('bs.modal', null);
				
			});
		});
		
$('#buy-coin-btn').click(function(e) {
			
			let modal = $('#modal_buy_coin');
			 
			$(modal).modal("show");
			
			let modalY = $('#modal_buy_coin #modalY');
			console.log($(modalY).text())
			$(modalY).click(function(e) {
				$(modal).modal('hide').data('bs.modal', null);
				location.href = "/shop/shop_view";
			});
		});
	</script>
			</div>
			<hr>

			<%-- 내 글 보기 --%>
			<div class="postSection row">
				<div class="community_list">
					<c:forEach var="board" items="${myBoardList.toList()}">
						<div class="post" onclick="location.href='/post/post_read/${board.id}'">
							<div class="img">
								<img src="${board.user.picture }">
							</div>

							<div class="txt">
								<div class="writer">${board.title}</div>
								<div class="comment">${board.content}</div>
								<div class="view">
									<span>추천수0</span><span>조회수:0</span>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="createPost">
					<h4>${boardUser.nickname} 님의<br>일상을 공유합니다</h4>
				</div>
			</div>
		</c:when>

		<%-- ***내정보 프로필*** --%>
		<c:otherwise>
			<input type="hidden" id="userno" value="${user.userno }">
			<%-- 프로필 사진 --%>
			<div class="pictureSection">
				<c:choose>
					<c:when test="${!empty user.picture && 'profileImg.jpg' ne user.picture}">
						<img id="img" class="profileImg" src="${user.picture }">
						<div class="spinner-border text-secondary" id="loading" role="status">
							<span class="sr-only">Loading...</span>
						</div>
					</c:when>
					<c:otherwise>
						<img id="img" class="profileImg" src="/image/profileImg.jpg">
					</c:otherwise>
				</c:choose>
				<form action="/profile/updatePicture" id="form" method="post" enctype="multipart/form-data">
					<sec:csrfInput />
					<input type="file" name="file" id="file" accept=".gif, .jpg, .png" style="display: none">
				</form>
				<button class="picUpdateBtn" id="picUpdateBtn">사진변경</button>
				<button class="picDeleteBtn" id="picDeleteBtn">기존사진 삭제</button>
			</div>

			<%-- 닉네임 및 내 정보 --%>
			<div class="infoSection">
				<div class="nicknameSection">
					<form>
						<input class="nickName" name="nickName" type="text" id="nickName" value=${user.nickname }>
						<button type="button" id="nicknameChange">닉네임 변경</button>
					</form>
				</div>
				<span> <label for="성별">성별 :</label> <select name="genderSelect" id="genderSelect" onchange="genderSelected()">
						<option value="" selected disabled hidden>${user.gender.toString() }</option>
						<option value="남">남</option>
						<option value="여">여</option>
				</select> &nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;나이 : <select name="ageSelect" id="ageSelect" onchange="ageSelected()">
						<option value="" selected disabled hidden>${user.age }</option>
						<script>
							for(i=0;i<=80;i++){
								document.write("<option>"+i+"</option>");
							}
						</script>
				</select> 세
				</span>
				<ul>
					<li>좋아요글 3</li>
					<li>나의 캐쉬 40 coin</li>
				</ul>

			</div>
			<br><br>
			<hr>

			<%-- 내 글 보기 --%>
			<div class="postSection row">
				<div class="community_list">
					<c:forEach var="board" items="${myBoardList.toList()}">
						<div class="post" onclick="location.href='/post/post_read/${board.id}'">
							<div class="img">
								<img src="${board.user.picture }">
							</div>

							<div class="txt">
								<div class="writer">${board.title}</div>
								<div class="comment">${board.content}</div>
								<div class="view">
									<span>추천수0</span><span>조회수:0</span>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="createPost">
					<h4>나의 일상을 공유해 보세요</h4>
					<button onclick="location.href='/post/post_write'">글쓰기</button>
				</div>
			</div>
		</c:otherwise>
	</c:choose>

</div>






<script>
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
					alert("닉네임 변경에 실패했습니다. 다시 시도해주세요.\n(2~8글자의 닉네임을 입력해 주세요.)");
					location.href = "/profile/profileMain"
				} else {
					alert("닉네임이 변경되었습니다.")
					location.href = "/profile/profileMain"
				}
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
					location.href = "/profile/profileMain"
				} else {
					alert("기존 사진이 삭제되었습니다.")
					location.href = "/profile/profileMain"
				}
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
					location.href = "/profile/profileMain"
				} else {
					alert("성별이 변경되었습니다.")
					location.href = "/profile/profileMain"
				}
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
					location.href = "/profile/profileMain"
				} else {
					alert("나이가 변경되었습니다.")
					location.href = "/profile/profileMain"
				}
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
	
</script>

<%@ include file="../layout/footer.jsp"%>

