<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

<div class="container">
	<h3>매출 통계</h3>
	<input type="date" id="dateSelect">
	<button id="monSales">월별 매출 조회</button>

	<canvas id="myChart"></canvas>

</div>



<script>
	var ctx = document.getElementById('myChart').getContext('2d');
	var chart = new Chart(ctx, {
		// 챠트 종류를 선택
		type : 'line',
		// 챠트를 그릴 데이타 
		data : {
			labels : [ 'January', 'February', 'March', 'April', 'May', 'June',
					'July' ],
			datasets : [ {
				label : 'My First dataset',
				backgroundColor : 'transparent',
				borderColor : 'red',
				data : [ 0, 0, 0, 0, 0, 0, 0 ]
			} ]
		},
		// 옵션
		options : {}
	});

	$('#monSales').click(
			function() {
				alert($('#dateSelect').val())

				var dateSelect = $('#dateSelect').val();

				$.ajax({

					url : "/api/sales/monSales",
					type : "POST",
					"headers" : headers,
					data : {
						"dateSelect" : dateSelect
					},
					success : function(res) {

						
						console.log(res);
						var data = [];
						$.each(res,function(key,val){
							
							console.log(val)
							data.push(val);
						})
						
						var ctx = document.getElementById('myChart')
								.getContext('2d');
						var chart = new Chart(ctx, {
							// 챠트 종류를 선택
							type : 'line',
							// 챠트를 그릴 데이타 
							data : {
								labels : [ 'January', 'February', 'March',
										'April', 'May', 'June', 'July','ag','se','oc','no','des' ],
								datasets : [ {
									label : 'My First dataset',
									backgroundColor : 'transparent',
									borderColor : 'red',
									data : data
								} ]
							},
							// 옵션
							options : {}
						});

					}

				})

			})
</script>
