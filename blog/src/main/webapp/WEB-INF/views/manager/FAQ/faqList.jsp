<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/manager/FAQ/faqList.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
<div align = "center">
	<div class="container1 ">
		<br> <br>
		<h3>문의 리스트</h3>
		<%-- <c:choose>
			<c:when test="${user.role == 'ADMIN' }"> --%>
				<div class="writeBtn">
					<button class="btn btn-warning"
						onclick="location.href='/manager/FAQ/faqWrite'">글쓰기</button>
				</div>
		<%-- 	</c:when>
		</c:choose> --%>
		<br>
		<table class="table">
			<thead class="thead-dark" align="center">
				<tr>
					<th class="noTh" scope="col">no</th>
					<th class="conTh" scope="col">내용</th>
				</tr>
			</thead>
			<tbody class="faq_list">
				<c:forEach var="dto" items="${list}">
					<tr>
						<td align = "center">
						${dto.no }
						</td>
						<td><a class="titleColor"
							href="/manager/notice/noticeView?no=${dto.no }">${dto.title }</a>
						</td>
					</tr>

				</c:forEach>

			</tbody>
		</table>
		<%-- <ul class="pagination justify-content-center">
			<c:forEach var="i" begin="1" end="${list.getTotalPages() }">
				<a class="ml-2 mr-2 paging_input">${i}</a>
			</c:forEach>
		</ul> --%>
	</div>
	
	<div class="container2 ">
		<br> <br>
		<h3>FAQ</h3>
		<%-- <c:choose>
			<c:when test="${user.role == 'ADMIN' }"> --%>
				<div class="writeBtn">
					<button class="btn btn-warning"
						onclick="location.href='/manager/FAQ/faqWrite'">글쓰기</button>
				</div>
		<%-- 	</c:when>
		</c:choose> --%>
		<br>
		<table class="table">
			<thead class="thead-dark" align="center">
				<tr>
					<th class="noTh" scope="col">no</th>
					<th class="conTh" scope="col">내용</th>
				</tr>
			</thead>
			<tbody class="faq_list">
				<c:forEach var="dto" items="${list}">
					<tr>
						<td align = "center">
						${dto.no }
						</td>
						<td><a class="titleColor"
							href="/manager/notice/noticeView?no=${dto.no }">${dto.title }</a>
						</td>
					</tr>

				</c:forEach>

			</tbody>
		</table>
		<%-- <ul class="pagination justify-content-center">
			<c:forEach var="i" begin="1" end="${list.getTotalPages() }">
				<a class="ml-2 mr-2 paging_input">${i}</a>
			</c:forEach>
		</ul> --%>
	</div>
</div>
	<%@ include file="../manager_footer.jsp"%>
</body>
<script src="/js/paging/noticePage.js"></script>
</html>