<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="manager_header.jsp"%>

<c:choose>
	<c:when test="${res == 1 }">
		<script>
			alert("해당글이 삭제 되었습니다.");
			location.href = "/manager/noticeList";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("삭제 실패!!!");
			history.back();
		</script>
	</c:otherwise>
</c:choose>


<%@ include file = "manager_footer.jsp" %>