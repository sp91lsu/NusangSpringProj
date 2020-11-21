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
			<div class="modal-body"></div>
			<div class="star-box">
				<span class="star star_left"></span> <span class="star star_right"></span> <span class="star star_left"></span> <span class="star star_right"></span> <span class="star star_left"></span> <span class="star star_right"></span> <span class="star star_left"></span> <span class="star star_right"></span> <span class="star star_left"></span> <span class="star star_right"></span>
			</div>
			<input type="hidden" id="star_evaluation">
			<div class="modal-footer">
				<a class="btn" id="modalY">평가하기</a>
				<button class="btn" id="modal_noBtn" type="button" data-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(".star").on('click', function() {
		var idx = $(this).index();
		$(".star").removeClass("on");
		for (var i = 0; i <= idx; i++) {
			$(".star").eq(i).addClass("on");
			console.log((idx + 1) * 0.5);
			$("#star_evaluation").val((idx + 1) * 0.5);
		}
	});
</script>

<%-- 
	다른 스크립트에서 이 창을 불러오고 싶을 때 
	
	
	
	//불러오기 
   <jsp:include page="../0_common/modal_star.jsp" flush="true">
		<jsp:param name="modalId" value='<%=URLEncoder.encode("modal_star", "UTF-8")%>' />
		<jsp:param name="title" value='<%=URLEncoder.encode("평가하기", "UTF-8")%>' />
		<jsp:param name="body" value='<%=URLEncoder.encode("", "UTF-8")%>' />
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

