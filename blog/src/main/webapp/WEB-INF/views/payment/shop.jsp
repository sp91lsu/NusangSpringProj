<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<h1 class="display-4 text-center mt-5 mb-5">상점</h1>
	<div class="d-flex justify-content-between">
		<c:forEach var="item" items="${coin_shop.itemList }">

			<div class="card mr-5 " style="width: 18rem;">
				<div class="card text-center" style="width: 90%; float:none; margin: 10px;">
					<img class="card-img-top" src="${item.picture }" alt="Card image cap">
				</div>
				<div class="card-body">
					<p class="card-text">${item.num }${item.type.name }가격:${item.price }원</p>
				</div>
			</div>


		</c:forEach>
	</div>


</div>




<%@ include file="../layout/footer.jsp"%>
</body>
<script type="text/javascript" src="/js/payment/shop.js">
	
</script>



</html>


