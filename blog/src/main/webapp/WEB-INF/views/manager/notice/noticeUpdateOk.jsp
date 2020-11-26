<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<c:choose>
	<c:when test="${res == 1 }">
		<script>
			alert("수정이 완료 되었습니다.");
			location.href = "/manager/notice/noticeView?no=${notice.no}";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("수정실패ㅠㅠㅠㅠ");
			history.back();
		</script>
	
	</c:otherwise>


</c:choose>








