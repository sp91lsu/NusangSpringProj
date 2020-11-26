<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>

<div class="container">

	<div class="btn_write container">
		<button class="btn btn-primary" onclick="location.href='/post/post_write'">글쓰기</button>
	</div>

	<div class="community_list container">
		<c:forEach var="board" items="${boards.content}">
			<div class="post" onclick="location.href='/post/post_read/${board.id}'">
				${board.user.picture}
				<div class="img">

					<img src="/upload/${board.user.picture}" alt="" />
				</div>

				<div class="txt">
					<div class="writer">${board.user.nickname}</div>
					<div class="comment">${board.content}</div>
					<div class="view">
						<span>추천수0</span><span>조회수:0</span>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

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

