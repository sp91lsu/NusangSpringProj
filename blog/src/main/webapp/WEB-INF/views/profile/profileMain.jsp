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
				<div class="createPost sticky-top">
					<h4>${boardUser.nickname} 님의<br>일상을 공유합니다</h4>
				</div>
			</div>
		</c:when>

		<%-- ***내정보 프로필*** --%>
		<c:otherwise>
			<%-- 프로필 사진 --%>
			<div class="pictureSection">
				<input type="hidden" id="userno" name="userno"  value="${user.userno }">
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
					<input type="hidden" id="userno" name="userno"  value="${user.userno }">
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
					<li>나의 코인: <a class="myCoin" href="/shop/shop_view">${user.coin } CM</a></li>
				</ul>

			</div>
			<br><br>
			<hr>

			<%-- 내 글 보기 --%>
			<div class="postSection row">
				<div class="community_list">
					<c:choose>
						<c:when test="${empty myBoardList.toList()}">
							<div class="emptyPost">등록된 일상이 없습니다.</div>
						</c:when>
						<c:otherwise>
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
						</c:otherwise>
					</c:choose>
				</div>
				<div class="createPost sticky-top">
					<h4>나의 일상을 공유해 보세요</h4>
					<button onclick="location.href='/post/post_write'">글쓰기</button>
				</div>
			</div>
		</c:otherwise>
	</c:choose>

</div>

<script type="text/javascript" src="/js/profile/profileMain.js" ></script> 
<script>
	

</script>
<%@ include file="../layout/footer.jsp"%>

