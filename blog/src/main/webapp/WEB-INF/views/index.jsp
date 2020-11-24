<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>

<div class="container">

	<%@ include file="post/post_read.jsp"%>

	<ul class="pagination justify-content-center">

		<c:choose>
			<c:when test="${boards.first }">
				<li class="page-item disabled"><a class="page-link" href="?page=${boads.number-1}">before</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boads.number-1}">before</a></li>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${boards.last }">
				<li class="page-item disabled"><a class="page-link" href="?page=${boads.number+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item "><a class="page-link" href="?page=${boads.number+1}">Next</a></li>
			</c:otherwise>
		</c:choose>

	</ul>
</div>
<br />
<%@ include file="layout/footer.jsp"%>
<script src="/js/user.js"></script>
</body>
</html>

