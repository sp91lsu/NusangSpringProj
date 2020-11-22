<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 시큐리티 검증이 완료됐다면 principal 을 가져온다. -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"></sec:authentication>
</sec:authorize>

<c:set var="user" value="${ principal.user}"></c:set>


<!DOCTYPE html>
<html lang="en">
<head>
<title>mycom blog</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

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
						<li class="nav-item"><a class="nav-link" href="/board/writeForm">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/updateForm">회원정보보기</a>
						<li id="logout" class="nav-item"><a class="nav-link" href="/logout">로그아웃</a>
						<li class="nav-item"><a class="nav-link" href="/chat/chatpage">채팅하기</a>
						<li class="nav-item"><a class="nav-link" href="/user/search">회원검색</a>
						<li class="nav-item"><a class="nav-link" href="/user/all_userlist">모든 유저</a>
						<li class="nav-item"><a class="nav-link" href="/friend/friends_view">내 친구목록</a>
						<li class="nav-item"><a class="nav-link" href="/user/search_location">위치검색</a>
							<div class="collapse navbar-collapse" id="navbarSupportedContent" style="font-family: 'Noto Sans KR', sans-serif;">
								<div class="navbar-nav pull-right" style="margin-left: -10%">

									<li class="nav-item"><a class="nav-link" href="/myinfo/profile" style="color: #22741C">${user.nickname} <span style="color: black">님</span>
									</a></li>
									<li class="nav-item"><button id="logoutBtn" type="button" class="btn btn-secondary btn-sm" onclick="location.href='/3_account/logout.jsp'">로그아웃</button></li>
									<li class="nav-item"><div class="dropdown">
											<button class="btn btn-outline-dark dropdown-toggle btn-sm" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${user.location != null ? user.location.name3 : '위치를 설정해주세요' }</button>
											<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
												<a class="dropdown-item" href="/myinfo/search">위치 설정</a>
											</div>
										</div></li>
									<li class="nav-item">
										<div class="dropdown">
											<button class="btn btn-outline-dark dropdown-toggle btn-sm" type="button" id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
												검색반경:
												<%-- ${user.view_distance} --%>
												km
											</button>
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
					</ul>

				</c:otherwise>
			</c:choose>
		</div>
	</nav>
	<br>