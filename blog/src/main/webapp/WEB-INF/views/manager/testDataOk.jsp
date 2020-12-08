<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
			alert("데이터 삽입실패 !!!");
		</script>
<c:choose>
	<c:when test="${result == 0 }">
		<script>
			alert("데이터 삽입실패 !!!");
			history.back();
		</script>
	</c:when>

	<c:otherwise>
		<script>
			alert("데이터 삽입 성공, 홈페이지로 갑니다");
			location.href = "/home";
		</script>
	</c:otherwise>
</c:choose>
