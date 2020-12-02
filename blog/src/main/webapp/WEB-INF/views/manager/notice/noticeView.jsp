<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/manager/notice/noticeView.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br>
	<br>
	<!-- <h3 style = "margin-left:235px;">공지사항</h3><br><br> -->
	<div id="container">

		<div id="titleDate" align="center" class="col-sm-4">
			<span id="titleFont">${view.title } </span><br> ${view.regdate }
			<br>
		</div>
		<div id="contents" class="col-sm-7">${view.contents }</div>
		<hr>

		<div align="center">
			<form action="/manager/notice/noticeDeleteOk" method="post">
				<sec:csrfInput />
				<c:choose>
					<c:when test="${user.role == 'ADMIN' }">
						<button type="button" class="btn btn-info"
							onclick="location.href = '/manager/notice/noticeUpdate?no=${view.no}'">수정
						</button>
					</c:when>
				</c:choose>
				<button type="button" class="btn btn-primary"
					onclick="location.href = '/manager/notice/noticeList'">목록으로
				</button>
				<input type="hidden" name="no" value="${view.no }" />
				<c:choose>
					<c:when test="${user.role == 'ADMIN' }">
						<button type="submit" class="btn btn-secondary">삭제</button>
					</c:when>
				</c:choose>
			</form>
		</div>
	</div>
	<%@ include file="../../layout/footer.jsp"%>
</body>
</html>