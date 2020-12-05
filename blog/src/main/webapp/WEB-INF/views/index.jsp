<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>

<div class="container">

	<%@ include file="post/post_list.jsp"%>
	
	총 페이지: ${myPostList.getTotalPages()}<br>
	현재 페이지 : ${myPostList.getNumber()+1}<br>
	총 글 갯수 : ${myPostList.getTotalElements()}<br>
	현재 페이지 글 갯수 : ${myPostList.toList().size()}<br>
	<ul class="pagination justify-content-center">
		<c:forEach var="i" begin="1" end="${myPostList.getTotalPages()}">
		
			<c:choose>
				<c:when test="${myPostList.getNumber()+1 eq i}">
					${i}
				</c:when>
				
				<c:otherwise>
					<a class="ml-2 mr-2 paging_num">${i}</a>
				</c:otherwise>
			</c:choose>
			
		</c:forEach>
	</ul>
</div>
<br>
<%@ include file="layout/footer.jsp"%>
<script type="text/javascript" src="/js/post/paging.js">

</script>
</body>
</html>

