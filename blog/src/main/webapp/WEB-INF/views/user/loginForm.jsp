<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<form action="/auth/loginProc" method="post">

		<div class="form-group">
			<label for="username">userName :</label> <input name="username" type="text" class="form-control" placeholder="Enter username" id="username" required="required">
		</div>

		<div class="form-group">
			<label for="password">Password:</label> <input name="password" type="password" class="form-control" placeholder="Enter password" id="password" required="required">
		</div>
		<sec:csrfInput />
		<button type="submit" class="btn btn-primary">로그인</button>
		<a href="/oauth2/authorization/kakao"><img height="38px" src="/image/kakaoLoginButton.png"></a> 
		<a href="/oauth2/authorization/google">구글 로그인</a> 
		<a href="/oauth2/authorization/facebook">페이스북 로그인</a> 
		<a href="/oauth2/authorization/naver">네이버 로그인</a>

	</form>

</div>
<br />

<%@ include file="../layout/footer.jsp"%>
</body>
</html>


