<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>



<link rel="stylesheet" href="/css/near_userlist.css" />
<div class="container display-4 mt-5 mb-5" style="height: 800px;">

	<h1>근처 회원</h1>
	<input type="hidden" id="mylongtitude" value="${user.location.longtitude }" />
	<input type="hidden" id="mylatitude" value="${user.location.latitude }" />
	<br>
	<c:forEach var="user_i" items="${nearUserList}">
	<c:if test="${!user_i.isMe(user_i) }">
		<div class="watchBox mr-5" style="float: left;">
			<div class="watch">
				<div class="user_img_wrapper">
					<img id="card_img" width="150px" src="${user_i.picture }" class="rounded-bottom">
				</div>
				<div class="textBox" style="font-family: 'Noto Sans KR', sans-serif; margin-left: 5px;">
					<h5 class="productname" style="margin-top: 10px;">${user_i.nickname }
						
			
					</h5>
						
						<input type="hidden" id="longtitude" value="${user_i.location.longtitude }" />
						<input type="hidden" id="latitude" value="${user_i.location.latitude }" />
					<h5 type="text" id="distance" ></h5>
				</div>
				<button onclick="location.href='/profile/profileMain/${user_i.userno}'" class="move btn btn-warning">프로필 보러가기</button>
			</div>
		</div>
		</c:if>
	</c:forEach>

</div>
<script>
	var myLocation = {

		latiDegr : $("#mylatitude").val(),
		longDegr : $("#mylongtitude").val()
	}
	
	console.log(myLocation.latiDegr)
	console.log(myLocation.longDegr)
	var rad = function(x) {
		return (x * Math.PI) / 180;
	};

	var getDistance = function(p1, p2) {
		var R = 6378137; // Earth’s mean radius in meter
		var dLat = rad(p2.latiDegr - p1.latitude);
		var dLong = rad(p2.longDegr - p1.longitude);
		var a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(rad(p1.latitude)) * Math.cos(rad(p2.latiDegr))
				* Math.sin(dLong / 2) * Math.sin(dLong / 2);
		var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		var d = R * c ;
		
		var regexp = /\B(?=(\d{3})+(?!\d))/g; 
		
		return parseInt(d).toString().replace( regexp, ',' ) + 'm';

	};

	$(".watchBox").each(function(i, element) {
		var location = {

				latitude : $(element).find("#latitude").val(),
				longitude : $(element).find("#longtitude").val()
			}
		console.log(location.latitude)
		console.log(location.longitude)
		$(element).find("#distance").html(getDistance(location,myLocation));
	})
</script>


<%@ include file="../layout/footer.jsp"%>
</body>

<script type="text/javascript" src="/js/user/allUserList.js">
	
</script>
</html>


