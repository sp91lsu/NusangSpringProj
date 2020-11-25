<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="manager_header.jsp"%>
<!DOCTYPE html>
<html>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div class="container " style="font-family: 'Noto Sans KR', sans-serif;">
		<br>
		<br>
		<caption>
			<h3>공지사항</h3>
		</caption>
		<div style="float: right;">
			<button class="btn btn-warning"
				onclick="location.href='/manager/noticeWrite'">글쓰기</button>
		</div>
		<br>
		<table class="table ">
			<thead class="thead-dark" align="center">
				<tr>
					<th style="width: 300px;" scope="col">내용</th>
					<th style="width: 100px;" scope="col">등록일</th>
				</tr>
			</thead>
			<tbody class="notice_list">
				<c:forEach var="dto" items="${list.toList() }">
					<tr>
						<td><a style="color: black;"
							href="/manager/noticeView?no=${dto.no }">${dto.title }</a></td>
						<td align="center">${dto.regdate }</td>
					</tr>

				</c:forEach>

			</tbody>
		</table>
		<c:forEach var="i" begin="1" end="${list.getTotalPages() }">

			<a class="ml-2 mr-2 paging_input">${i}</a>

		</c:forEach>
	</div>


</body>
<script src="/js/paging/noticePage.js"></script>
</html>