<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%
	request.setCharacterEncoding("UTF-8");
String modalId = URLDecoder.decode(request.getParameter("modalId"), "UTF-8");
String title = URLDecoder.decode(request.getParameter("title"), "UTF-8");
String body = URLDecoder.decode(request.getParameter("body"), "UTF-8");
%>

<!-- 회원가입 확인 Modal-->
<div class="modal fade" id=<%=modalId%> tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal" aria-label="Close"></button>
				<h3 class="modal-title" id="exampleModalLabel"><%=title%></h3>
			</div>
			<div class="modal-body"><%=body%></div>
			<div class="modal-footer">
				<a class="btn" id="modalY">예</a>
				<button class="btn" id="modal_noBtn" type="button" data-dismiss="modal">아니요</button>
			</div>
		</div>
	</div>
</div>


<%-- 
	다른 스크립트에서 이 창을 불러오고 싶을 때 
	
	
	
	//불러오기 
   <jsp:include page="../0_common/modal.jsp" flush="true">
		<jsp:param name="modalId" value='<%=URLEncoder.encode("modal_loc", "UTF-8")%>' />
		<jsp:param name="title" value='<%=URLEncoder.encode("위치 설정", "UTF-8")%>' />
		<jsp:param name="body" value='<%=URLEncoder.encode("현재위치로 설정하시겠습니까?", "UTF-8")%>' />
	</jsp:include>

	//우리가 쓸 페이지
	<button class="modalBtn" class="btn">button</button>

	<script>
		$('#modalBtn_1').click(function(e) {
			
			let modal = $('#modal_loc');
			 
			$(modal).modal("show");
			
			let modalY = $('#modal_loc #modalY');
			console.log($(modalY).text())
			$(modalY).click(function(e) {
				console.log("fdasfsda")
				$(modal).modal('hide').data('bs.modal', null);
				//여기에서 예를 눌렀을 떄 수행해야함
			});
		});
	</script>

 --%>

