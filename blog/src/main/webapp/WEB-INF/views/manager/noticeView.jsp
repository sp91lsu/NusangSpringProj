<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="manager_header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
<br>
<div class = "container" style = "width:700px; min-height:500px; border:1px solid lightgray;">
제목 : ${view.title } <br>
날짜 : ${view.regdate } <br>
내용 : ${view.contents }


</div>
<div align = "center">
<button class = "btn btn-primary" onclick = "location.href = '/manager/noticeUpdate?no=${view.no}'">수정</button>
<button class = "btn btn-secondary" onclick = "location.href = '/manager/noticeDelete?no${view.no}'">삭제</button>
</div>
<%@ include file = "manager_footer.jsp" %>
</body>
</html>