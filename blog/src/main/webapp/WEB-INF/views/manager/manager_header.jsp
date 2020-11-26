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
<title>manager blog</title>
<meta charset="utf-8">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Load font awesome icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

<link rel="stylesheet" href="/css/manager/noticeView.css" />
<link rel="stylesheet" href="/css/manager/header_subnav.css" />

<link rel="stylesheet" href="/css/manager/noticeList.css" />
<body style="height:1500px">
	<div class="container-fluid">
	  <br>
	  <h3>___관리자님 환영합니다!</h3>
	  <p>A sticky navigation bar stays fixed at the top of the page when you scroll past it.</p>
	  <p>Scroll this page to see the effect. <strong>Note:</strong> sticky-top does not work in IE11 and earlier.</p>
	</div>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
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
					<ul class="navbar-nav overfH">
						<li class="nav-item"><a class="nav-link" href="">일반사용자 메뉴</a></li>
						<li class="nav-item subnav">
							<a class="nav-link subnav-li" href="">회원관리</a>
							<div class="subnav-content">
								<a href="#bring">회원검색</a>
								<a href="#deliver">블랙리스트</a>
								<a href="#package">회원동향 분석</a>
							</div>
						</li>
						<li class="nav-item subnav">
							<a class="nav-link subnav-li" href="">매출분석</a>
							<div class="subnav-content">
								<a href="#bring">매출통계</a>
								<a href="#deliver">지역별 매출통계</a>
							</div>
						</li>
						<li class="nav-item subnav">
							<a class="nav-link subnav-li" href="">게시판관리</a>
							<div class="subnav-content">
								<a href="/manager/noticeList">공지사항</a>
								<a href="">FAQ</a>
							</div>
						</li>
					</ul>
					<ul class="navbar-nav ml-auto">
						<li id="logout" class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>
	<div class="subnav-content_bellow"></div>
	<br>
	<script type="text/javascript">
		$(function(){
			 var sub = $(".subnav");
			 var con = $(".subnav-content");
			 
			 sub.hover(
	    		 function(){
	    			 var lt = $(this).position().left;
	    			 con.css("padding-left", lt);
    			 },
    			 function(){
    				 con.css("padding-left", "0");
    			 }
    		 );
		});
	</script>