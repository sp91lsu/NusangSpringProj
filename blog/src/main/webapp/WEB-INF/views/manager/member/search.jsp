<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>
<link rel="stylesheet" href="/css/manager/search.css" />
<body>
	<div class="myContainer">
		<div class="d-flex">
			<!-- <div id="sortDiv" class="d-flex">정렬순서: &nbsp;<div id="sortOrder">아아</div></div> -->
			<button id="searchBtn" class="ml-auto btn btn-success" >검색</button>
		</div>
		<div id="searchBody">
			<table>
				<thead>
					<tr>
						<form id="valForm" action="/manager/member/searched" method="get">
							<th><div><input type="number" name="no_min" min="0" max="1000000000"><br><i class="fas fa-ellipsis-v"></i><br><input type="number" name="no_max" min="0" max="1000000000"></div></th>
							<th><div><input name="name"></div></th>
							<th><div><input name="id"></div></th>
							<th><div><input name="pw"></div></th>
							<th><div><input name="nic"></div></th>
							<th><div><input type="number" name="age_min"><br><i class="fas fa-ellipsis-v"></i><br><input type="number" name="age_max"></div></th>
							<th><div><select id="gender" name="gender"><option value="ALL">ALL</option><option value="MALE">MALE</option><option value="FEMALE">FEMALE</option></select></div></th>
							<th><div><input name="email"></div></th>
							<th><div><input type="number" name="coin_min"><br><i class="fas fa-ellipsis-v"></i><br><input type="number" name="coin_max"></div></th>
							<th><div><input type="number" name="tPay_min"><br><i class="fas fa-ellipsis-v"></i><br><input type="number" name="tPay_max"></div></th>
							<th><div><input type="number" name="talk_min"><br><i class="fas fa-ellipsis-v"></i><br><input type="number" name="talk_max"></div></th>
							<th><div><input type="date" name="date_min"><br><i class="fas fa-ellipsis-v"></i><br><input type="date" name="date_max"></div></th>
							<th><div><select id="role" name="role"><option value="ALL">ALL</option><option value="USER">USER</option><option value="ADMIN">ADMIN</option></select></div></th>
						</form>
					</tr>
				</thead>
				<thead>
					<tr>
						<th><span>회원번호</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
						<th><span>이름</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
						<th><span>아이디</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
						<th><span>비밀번호</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
						<th><span>닉네임</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
						<th><span>나이</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
						<th><span>성별</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
						<th><span>이메일</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
						<th><span>코인</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
						<th><span>총결제금액</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
						<th><span>이용가능토크</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
						<th><span>회원가입일</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
						<th><span>권한</span><div class="caretBox"><i class="caretdown fas fa-angle-double-down"></i><i class="caretup fas fa-angle-double-up"></i></div></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="user">
						<tr>
							<td>${user.userno }</td>
							<td>${user.username }</td>
							<td>${user.userid }</td>
							<td>${user.password }</td>
							<td>${user.nickname }</td>
							<td>${user.age }</td>
							<td>${user.gender }</td>
							<td>${user.email }</td>
							<td>${user.coin }</td>
							<td>${user.totalPay }</td>
							<td>${user.availableTalk }</td>
							<td>${user.createDate }</td>
							<td>${user.role }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
		
		<%-- 총 페이지: ${myPostList.getTotalPages()}<br>
		현재 페이지 : ${myPostList.getNumber()+1}<br>
		총 글 갯수 : ${myPostList.getTotalElements()}<br>
		현재 페이지 글 갯수 : ${myPostList.toList().size()}<br> --%>
		
		<%-- <ul class="pagination justify-content-center">
			<c:forEach var="i" begin="1" end="${searchedList.getTotalPages()}">
			
				<c:choose>
					<c:when test="${searchedList.getNumber()+1 eq i}">
						${i}
					</c:when>
					
					<c:otherwise>
						<a class="ml-2 mr-2 paging_num">${i}</a>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
		</ul> --%>
	</div>
	<script type="text/javascript" src="/js/manager/search.js"></script>
	<script type="text/javascript" src="/js/manager/paging.js"></script>
	<%-- <%@ include file="../../layout/footer.jsp"%> --%>
</body>
</html>