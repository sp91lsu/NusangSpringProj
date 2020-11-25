<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>

<div class="container">

	<%@ include file="post/post_list.jsp"%>

	현재 페이지 : ${boards.number }
	<ul class="pagination justify-content-center">

		<%-- <c:choose>
			<c:when test="${boards.first }">
				<li class="page-item disabled before_btn"><a class="page-link">before</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item paging_input before_btn"><a class="page-link">before</a><input
					id="paging_value" type="hidden" value="${boards.number -1 }" /></li>
			</c:otherwise>
		</c:choose> --%>
		<c:forEach var="i" begin="1" end="${boards.getTotalPages() }">

			<a class="ml-2 mr-2 paging_input">${i}</a>

		</c:forEach>
		<%-- <c:choose>
			<c:when test="${boards.last }">
				<li class="page-item disabled"><a class="page-link">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item paging_input"><a class="page-link">Next</a>
					<input id="paging_value" type="hidden" value="${boards.number +1 }" /></li>
			</c:otherwise>
		</c:choose> --%>

	</ul>
</div>
<br />
<%@ include file="layout/footer.jsp"%>
<script type="text/javascript" src="/js/paging/paging.js">
<!--
	
//-->
</script>
</body>
</html>

