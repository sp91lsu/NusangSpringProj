<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/manager/notice/noticeList.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div class="container ">
		<br> <br>
		<h3>공지사항</h3>
		<c:choose>
			<c:when test="${user.role == 'ADMIN' }">
				<div class="writeBtn">
					<button class="btn btn-warning"
						onclick="location.href='/manager/notice/noticeWrite'">글쓰기</button>
				</div>
			</c:when>
		</c:choose>
		<br>
		<table class="table ">
			<thead class="thead-dark" align="center">
				<tr>
					<th class="conTh" scope="col">내용</th>
					<th class="dateTh" scope="col">등록일</th>
				</tr>
			</thead>
			<tbody class="notice_list">
				<c:forEach var="dto" items="${list.toList() }">
					<tr>
						<td><a class="titleColor"
							href="/manager/notice/noticeView?no=${dto.no }">${dto.title }</a></td>
						<td align="center">${dto.regdate }</td>
					</tr>

				</c:forEach>

			</tbody>
		</table>
		<ul class="pagination justify-content-center">
			<c:forEach var="i" begin="1" end="${list.getTotalPages() }">
				<a class="ml-2 mr-2 paging_input">${i}</a>
			</c:forEach>
		</ul>
	</div>

	<%@ include file="../manager_footer.jsp"%>
</body>
<script src="/js/paging/noticePage.js"></script>
</html>