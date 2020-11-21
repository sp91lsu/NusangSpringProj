<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/css/error.css">

<c:set var="error" value="${requestScope.error}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR</title>
</head>
<body>

	<div class="container d-flex justify-content-center flex-column" style="min-height: 500px;">
		<div id="imgDiv" class="d-flex justify-content-center">
			<img id="errorImg" src="/img/errorImg.png" alt="errorImg">
		</div>
		<div class="d-flex justify-content-center">
			<div id="content">${error}</div>
		</div>
	</div>


</body>

</html>


