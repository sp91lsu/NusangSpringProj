<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${res == 1 }">
			<script>
				alert("문의하신 내용이 관리자에게 전송되었습니다.");
			</script>	
		</c:when>
		<c:otherwise>
			<script>
				alert("문의실패!!!");
			</script>
		</c:otherwise>
	
	</c:choose>
	
	
</body>
</html>