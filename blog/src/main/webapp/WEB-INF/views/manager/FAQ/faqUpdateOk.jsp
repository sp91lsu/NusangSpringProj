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
				alert("수정이 완료되었습니다.");
				location.href = "/manager/FAQ/faqList";
			</script>	
		</c:when>
		<c:otherwise>
			<script>
				alert("수정실패!!!");
				history.back();
			</script>
		</c:otherwise>
	
	</c:choose>
	
	
</body>
</html>