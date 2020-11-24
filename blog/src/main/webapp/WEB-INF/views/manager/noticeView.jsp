<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="manager_header.jsp"%>
<!DOCTYPE html>
<html>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
<br>
<!-- <h3 style = "margin-left:235px;">공지사항</h3><br><br> -->
<div class = "container" style = "width:900px; min-height:500px;">

<div align = "center" class = "col-sm-5" style = "padding-top:100px;display: inline-block; float:left;height:500px; background:pink;">
<span style = "font-family: 'Noto Sans KR', sans-serif; font-weight: bold; font-size: 25px;">제목 : ${view.title } </span><br>
날짜 : ${view.regdate } <br>
</div>
<div class = "col-sm-7" style = "display: inline-block; height:500px; background: skyblue; word-break: break-all">
내용 : ${view.contents }
</div>

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