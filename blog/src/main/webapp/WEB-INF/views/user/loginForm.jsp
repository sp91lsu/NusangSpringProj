<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">userName :</label> 
			<input name="username" type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		
		<div class="form-group">
			<label for="password">Password:</label>
			 <input name="password" type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
	
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=08d4e0306ac821705c50a3083bf85370&redirect_uri=http://localhost:8000/auth/kakaologin&response_type=code"><img height="38px" src="/image/kakaoLoginButton.png"></a>
	</form>
	
</div>
<br />

<%@ include file="../layout/footer.jsp"%>
</body>
</html>


