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
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/css/manager/header_subnav.css" />
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

<c:choose>

	<c:when test="${user.role == 'ADMIN' }">
		<%@ include file="manager_header.jsp"%>
	</c:when>
	<c:otherwise>
		<%@ include file="userHeader.jsp"%>
	</c:otherwise>
</c:choose>

<script type="text/javascript" src="/js/common/header.js"></script>