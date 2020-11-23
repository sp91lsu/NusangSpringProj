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
<form action="/manager/noticeDeleteOk" method = "post">
<button type = "button" class = "btn btn-primary" onclick = "location.href = '/manager/noticeUpdate?no=${view.no}'">수정</button>
<button type = "button" class = "btn btn-primary" onclick = "location.href = '/manager/noticeList'">목록으로</button>
<input type="hidden" name = "no" value = "${view.no }" />
<button type = "submit" class = "btn btn-secondary">삭제</button>
</form>
</div>
<%@ include file = "manager_footer.jsp" %>
</body>
</html>