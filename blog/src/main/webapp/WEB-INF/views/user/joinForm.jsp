<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<form>
		<div class="form-group">
			<label for="username">userName :</label> <input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="userid">userId :</label> <input name="userid" type="text" class="form-control" placeholder="Enter userid" id="userid">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="email">email :</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>

	</form>
	<button id="btn-save" class="btn btn-primary">회원 가입</button>
</div>

<script type="text/javascript" src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
</body>
</html>


