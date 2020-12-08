<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/manager/FAQ/faqList.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

</head>
<body>
	<input type="hidden" id="page_owner"
		value="${user.role == 'ADMIN' ? 'manager' : 'user' }" />
	<div align="center" class="d-flex">
		<div class="container1 p-6 ">
			<c:choose>
				<c:when test="${user.role == 'ADMIN' }">
					<!-- --------------------------문의 리스트----------------------------------------- -->
					<br>
					<br>
					<br>
					<h3>문의 리스트</h3>
					<%-- <c:choose>
			<c:when test="${user.role == 'ADMIN' }"> --%>
					<div class="btnhidden">줄맞춤용</div>
					<%-- 	</c:when>
		</c:choose> --%>
					<br>

					<table class="table">
						<thead class="thead-dark" align="center">
							<tr>
								<th class="qna_noTh" scope="col">no</th>
								<th class="qna_conTh2" scope="col">내용</th>
								<th class="qna_nickname" scope="col">닉네임</th>
								<th class="qna_date" scope="col">문의날짜</th>
								<th class="qna_ans" scope="col">답변여부</th>
							</tr>
						</thead>
						<tbody class="qna_list">
							<c:forEach var="qna" items="${qnaList.toList()}">

								<tr>
									<td align="center">${qna.no }</td>
									<td>
										<div class="titleColor">${qna.title }</div>
									</td>
									<td align = "center" >${qna.me.nickname }</td>
									<td align = "center" ><fmt:formatDate value="${qna.regdate}"
											pattern="yy-MM-dd HH:mm" /></td>
									<c:choose>
										<c:when test="${qna.answer == null }">
											<td class="answerChk1" align="center">미답변</td>
										</c:when>
										<c:otherwise>
											<td class="answerChk2" align="center">답변완료</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr class="contents">
									
									<td></td>
									
									<td class = "allContents" colspan="3"><br>${qna.contents }<br>
										<form class = "answerForm" action="/manager/QNA/qnaUpdateOk" method="post">
											<sec:csrfInput />
											<textarea name="answer" class="textarea answerText" cols="52" rows="7"
												placeholder="답변달기">${qna.answer}</textarea>
											<button type="button" class="answer btn btn-primary btn-sm answerBtn">답변달기</button>
											<input type="hidden" name="no" value="${qna.no }" />

										</form>
										</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

					<ul class="pagination justify-content-center">
						<c:forEach var="i" begin="1" end="${qnaList.getTotalPages() }">
							<a class="ml-2 mr-2 qna_paging">${i}</a>
						</c:forEach>
					</ul>

				</c:when>

				<c:otherwise>
					<!-- ----------------------------내 문의 내역---------------------------------------- -->
					<br>
					<br>
					<br>
					<h3>내 문의내역</h3>
					<br>

					<table class="table">
						<thead class="thead-dark" align="center">
							<tr>
								<th class="qna_noTh" scope="col">no</th>
								<th class="qna_conTh" scope="col">내용</th>
								<th scope="col">문의날짜</th>
								<th scope="col">답변여부</th>
							</tr>
						</thead>
						<tbody class="qna_list">
							<c:forEach var="qna" items="${qnaList.toList()}">
								<c:choose>
									<c:when test="${user.userno == qna.me.userno }">
										<tr>
											<td align="center">${qna.no }</td>
											<td>
												<div class="titleColor">${qna.title }</div>
											</td>
											<td align="center"><fmt:formatDate
													value="${qna.regdate}" pattern="yy-MM-dd HH:mm" /></td>
											<c:choose>
												<c:when test="${qna.answer == null }">
													<td class="answerChk1" align="center">미답변</td>
												</c:when>
												<c:otherwise>
													<td class="answerChk2" align="center">답변완료</td>
												</c:otherwise>
											</c:choose>
										</tr>
										<tr class="contents">
											<td></td>
											<td class = "myContents" colspan="2"><br>${qna.contents }<br>
												<form action="/manager/QNA/qnaUpdateOk" method="post">
													<sec:csrfInput />
													<c:choose>
														<c:when test="${qna.answer == null }">
														<textarea style = "color: #ABABAB;" name="answer" class="textarea" cols="52" rows="7" readonly>아직 답변이 달리지 않았습니다.</textarea>
														</c:when>
														<c:otherwise>
														
													<textarea name="answer" class="textarea" cols="52" rows="7" readonly>${qna.answer}</textarea>
														</c:otherwise>
													</c:choose>
													<input type="hidden" name="no" value="${qna.no}" /> <input
														type="hidden" value="${qna.me.userno}" />
												</form></td>
										</tr>
									</c:when>
								</c:choose>
							</c:forEach>

						</tbody>
					</table>
	
					<ul class="pagination justify-content-center">
						<c:forEach var="i" begin="1" end="${qnaList.getTotalPages() }">
							<a class="ml-2 mr-2 qna_paging">${i}</a>
						</c:forEach>
					</ul>
					<br>
					<hr>
					<!-- ----------------------------문의하기---------------------------------------- -->
					<br>
					<h3>문의하기</h3>

					<br>
					<form id="qnaWrite" action="/manager/QNA/qnaWriteOk" method="post">
						<sec:csrfInput />
						<div class="titleBox">
							문의제목&nbsp;&nbsp;&nbsp;<input id = "qnaTitle" name="title" class="qnaTitle"
								type="text" />
						</div>
						<textarea id = "qnaContents" class="textarea2" name="contents" cols="54" rows="6"
							placeholder="문의내용"></textarea>
						<input type="hidden" name="userno" value="${user.userno }" />
						<button id = "qnaBtn" type="button" class="btn btn-dark btn-sm qnaBtn">문의하기</button>
					</form>


				</c:otherwise>
			</c:choose>

	<!-- ----------------------------FAQ----------------------------------------- -->
		</div>
		<div class="container2 p-6">
			<br> <br><br>
			<h3>FAQ</h3>
			<c:choose>
				<c:when test="${user.role == 'ADMIN' }">
					<div class="writeBtn">
						<button class="btn btn-warning"
							onclick="location.href='/manager/FAQ/faqWrite'">글쓰기</button>
					</div>
				</c:when>
			</c:choose>
			<br>
			<form action="/manager/FAQ/faqDeleteOk" method="post">
				<sec:csrfInput />
				<table class="table">
					<thead class="thead-dark" align="center">
						<tr>
							<th class="faq_noTh" scope="col">no</th>
							<th class="faq_conTh" scope="col">내용</th>
						</tr>
					</thead>
					<tbody class="faq_list">
						<c:forEach var="dto" items="${faqList.toList()}">
							<tr>
								<td align="center">${dto.no }</td>
								<td>
									<div class="faqtitleColor">${dto.title }</div>
									<div class="contents">
										<br>${dto.contents }<br>
										<c:choose>
											<c:when test="${user.role == 'ADMIN' }">
												<button type="button" class="btn btn-primary btn-sm"
													onclick="location.href = '/manager/FAQ/faqUpdate?no=${dto.no}'">수정</button>
												<input type="hidden" name="no" value="${dto.no }" />
												<button type="submit" class="btn btn-secondary btn-sm">삭제</button>
											</c:when>
										</c:choose>
									</div>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</form>
			<ul class="pagination justify-content-center">
				<c:forEach var="i" begin="1" end="${faqList.getTotalPages() }">
					<a class="ml-2 mr-2 faq_paging">${i}</a>
				</c:forEach>
			</ul>
		</div>
	</div>
	<script>
	$(".answerBtn").click(function(){
		var answerText = $(this).parent(".answerForm").children(".answerText").val().trim();
		if(answerText == ""){
			alert("작성하신 답변 내용이 없습니다.");
		}else{
			//$(".answerForm").submit();
			$(this).parent(".answerForm").submit();
		}
	})
	
	$("#qnaBtn").click(function(){
		var qnaTitle = $("#qnaTitle").val().trim();
		var qnaContents = $("#qnaContents").val();
		
		//alert("내용 : " + contents);
		if((qnaTitle == "") || (qnaContents == "")){
			alert("제목과 내용을 모두 작성해주세요");
		}else if(qnaTitle.length > 28){
			alert("제목은 간략하게 작성해주세요.");
		}else{
			$("#qnaWrite").submit();
		}
	})
		// html dom 이 다 로딩된 후 실행된다.
		$(document).ready(function() {
			// menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
			$(".titleColor").click(function() {
				var submenu = $(this).parent().parent().next("tr");

				// submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
				submenu.toggle();
				/*  if (submenu.is(":visible")) {
					submenu.slideUp();
				} else {
					submenu.slideDown();
				}  */
			});
		});

		// html dom 이 다 로딩된 후 실행된다.
		$(document).ready(function() {
			// menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
			$(".faqtitleColor").click(function() {
				var submenu = $(this).next("div");

				// submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
				if (submenu.is(":visible")) {
					submenu.slideUp();
				} else {
					submenu.slideDown();
				}
			});
		});
	</script>
	<c:choose>
		<c:when test="${user.role == 'ADMIN' }">
			<script src="/js/paging/qnaListPage.js"></script>
		</c:when>
		<c:otherwise>
			<script src="/js/paging/myQNAPage.js"></script>
		</c:otherwise>
	</c:choose>
	<script src="/js/paging/faqPage.js"></script>
	<%@ include file="../../layout/footer.jsp"%>
</body>

<script src="/js/manager/faqList.js"></script>
<script src="/js/paging/noticePage.js"></script>
</html>