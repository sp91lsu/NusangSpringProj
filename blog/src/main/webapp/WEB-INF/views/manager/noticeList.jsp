<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="manager_header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <div class = "container">
 	<br><br>
	<caption><h3>공지사항</h3></caption>
	<div style = "float: right;"><button class = "btn btn-secondary" onclick = "location.href='/manager/noticeWrite'">글쓰기</button></div>
	<br>
<table class="table">
  <thead class="thead-dark" align = "center">
    <tr>
      <th style = "width:300px;" scope="col">내용</th>
      <th style = "width:100px;" scope="col">등록일</th>
    </tr>
  </thead>
  <tbody>
  		<c:forEach var="dto" items="${list }">
  			<tr>
  				<td><a href="/manager/noticeView?no=${dto.no }">${dto.title }</a> </td>
  				<td align = "center">${dto.regdate }</td>
  			</tr>
  		
  		</c:forEach>
  	
  </tbody>
</table>
<ul class="pagination justify-content-center">
  <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
  <li class="page-item"><a class="page-link" href="#">1</a></li>
  <li class="page-item"><a class="page-link" href="#">2</a></li>
  <li class="page-item"><a class="page-link" href="#">3</a></li>
  <li class="page-item"><a class="page-link" href="#">Next</a></li>
</ul>
</div>

</body>
</html>