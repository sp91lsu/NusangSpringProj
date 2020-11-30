<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<h1 class="display-4 text-center mt-5 mb-5">상점</h1>
	
	<div>
	<h3>내 cm : ${user.coin }</h3></div>
	<div class="row d-flex justify-content-between">
		<input type="hidden" id="user_email" value="${user.email}" />
		<input type="hidden" id="user_no" value="${user.userno}" />
		
		<c:forEach var="item" items="${coin_shop.itemList }">

			<div class="card mr-5 mt-5 product_item" style="width: 13rem;">
				<input type="hidden" id="item_price" value="${item.price }" /> <input type="hidden" id="itemno" value="${item.itemno}" />
				<div class="card text-center" style="width: 90%; float: none; margin: 10px;">
					<img class="card-img-top" src="${item.picture }" alt="Card image cap">
				</div>
				<input type="hidden" id="item_price" value="${item.price }" />
				<div class="card-body">
					<p class="card-text"> ${item.num }${item.type.name }가격:${item.price }원</p>
				</div>
			</div>


		</c:forEach>
	</div>


</div>



<jsp:include page="../layout/modalOneBtn.jsp" flush="true">
	<jsp:param name="modalId" value='<%=URLEncoder.encode("modal_buy_item", "UTF-8")%>' />
	<jsp:param name="title" value='<%=URLEncoder.encode("결제", "UTF-8")%>' />
	<jsp:param name="body" value='<%=URLEncoder.encode("결제가 완료되었습니다.", "UTF-8")%>' />
</jsp:include>

<button class="modalBtn" class="btn">button</button>

<%@ include file="../layout/footer.jsp"%>
</body>
<script type="text/javascript" src="/js/payment/shop.js">
	
</script>



</html>


