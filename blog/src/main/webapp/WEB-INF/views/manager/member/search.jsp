<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>
<link rel="stylesheet" href="/css/manager/search.css" />
<body>
	<div class="myContainer">
		<div class="d-flex">
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
						<th>회원번호</th>
						<th>이름</th>
						<th>아이디</th>
						<th>비밀번호</th>
						<th>닉네임</th>
						<th>나이</th>
						<th>성별</th>
						<th>이메일</th>
						<th>코인</th>
						<th>총결제금액</th>
						<th>이용가능토크</th>
						<th>회원가입일</th>
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
	</div>
	<script type="text/javascript" src="/js/manager/search.js"></script>
	<%-- <%@ include file="../../layout/footer.jsp"%> --%>
</body>
</html>