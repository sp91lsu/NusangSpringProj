<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/header.css" />
<link rel="stylesheet" href="/css/post_list.css" />
<link rel="stylesheet" href="/css/post_read.css" />

<body>
	<nav class="navbar navbar-expand-lg navbar-light" style="background: #FFF;; border-bottom: 3px solid #333">
		<a class="navbar-brand" href="/home">홈</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">

			<c:choose>
				<c:when test="${empty principal}">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/user/search">회원검색</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/all_userlist">모든 유저</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/near_userlist">내 근처회원</a></li>
						<li class="nav-item"><a class="nav-link" href="/friend/friends_view">친구관리</a></li>
						<li class="nav-item"><a class="nav-link" href="/shop/shop_view">아이템샵</a></li>
						<li class="nav-item"><a class="nav-link" href="/manager/notice/noticeList">공지사항</a></li>
						<li class="nav-item">
							<div class="collapse navbar-collapse" id="navbarSupportedContent" style="font-family: 'Noto Sans KR', sans-serif;">
								<div class="navbar-nav pull-right" style="margin-left: -10%">

									<li class="nav-item"><a class="nav-link" href="/profile/profileMain" style="color: #22741C">${user.nickname} <span style="color: black">님</span>
									</a></li>
									<li class="nav-item">
										<form action="/logout" method="post">
											<sec:csrfInput />
											<button id="logoutBtn" type="submit" class="btn btn-secondary btn-sm">로그아웃</button>
										</form>
									</li>

									<li class="nav-item"><div class="dropdown">
											<button class="btn btn-outline-dark dropdown-toggle btn-sm" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${user.location != null ? user.location.name3 : '위치를 설정해주세요' }</button>
											<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
												<a class="dropdown-item" href="/user/search_location">위치 설정</a>
											</div>
										</div></li>
									<li class="nav-item">
										<div class="dropdown">
											<button class="btn btn-outline-dark dropdown-toggle btn-sm" type="button" id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">검색반경: ${user.location.view_distance} km</button>
											<c:set var="numbers" value='<%=new String[] { "1", "3", "5", "10" }%>' />
											<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
												<c:forEach var="num" items="${numbers }">
													<a class="dropdown-item" onclick="sendNum('${num}')">${num}km</a>
												</c:forEach>
											</div>
										</div>
									</li>
								</div>
							</div>
						</li>
					</ul>

				</c:otherwise>
			</c:choose>
		</div>
	</nav>
	<br>