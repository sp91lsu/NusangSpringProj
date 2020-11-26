<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<div class="container d-flex justify-content-center flex-column" style="min-height: 500px;">
		<div id="imgDiv" class="d-flex justify-content-center">
			<img id="errorImg" src="/img/errorImg.png" alt="errorImg">
		</div>
		<div class="d-flex justify-content-center">
			<div id="content">${error}</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
</body>
</html>


