<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<form>
		<div class="form-group">
			<label for="username">userName :</label> <input type="text" class="form-control" placeholder="Enter username" id="username" value="${principal.user.username }" readonly="readonly">
		</div>
		<c:choose>
			<c:when test="${principal.user.authType == 'NORMAL'}">
				<div class="form-group">
					<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" value="${principal.user.password }">
				</div>
				<div class="form-group">
					<label for="email">email :</label> <input type="email" class="form-control" placeholder="Enter email" id="email" value="${principal.user.email }">
				</div>
			</c:when>
			<c:otherwise>
				
				<div class="form-group">
					<label for="email">email :</label> <input type="email" class="form-control" placeholder="Enter email" id="email" value="${principal.user.email }" readonly="readonly">
				</div>
				<h5>소셜 아이디는 수정이 불가능합니다.</h5>
			</c:otherwise>
		</c:choose>
	</form>
	<button id="btn-update" class="btn btn-primary">회원 수정 완료</button>
</div>

<script type="text/javascript" src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
</body>
</html>


