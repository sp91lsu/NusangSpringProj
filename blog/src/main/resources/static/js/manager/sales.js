$(function(){
	/*$(".tablinks").first().trigger("click");*/
});


function openCity(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}



$('.readBtn').click(function(){
	var ascen = $(this).parent().parent();
	var date1=ascen.find("input").eq(0).val();
	var date2=ascen.find("input").eq(1).val();
	var aid = ascen.attr("id")
	switch(aid){
		case "timeTab":
			timeChart(date1,date2);
			circleChart(date1,date2,aid);
			break;
		case "dateTab":
			dateChart();
			circleChart2(aid);
			break;
	/*	case "dayTab":
			dayChart(date1,date2);
			circleChart(date1,date2);
			break;*/
		case "monthTab":
			monthChart();
			circleChart3(aid);
			break;
		
	}
});
/*시간대별_바타입*/
function timeChart(date1,date2){
	var myChart;
	$.ajax({
		url : "/api/sales/timeChart",
		type : "POST",
			"headers":headers,
		data : {
			"date1" : date1,
			"date2" : date2
		},
		success : function(res) {
			console.log(res)
			var keys = [];
			var values = [];
			$.each(res, function(key, val) {
				keys.push(val.time);
				console.log(val.time);
				console.log(val.pay);
				values.push(val.pay);
			})
			 if (window.myChart != undefined)
{
    window.myChart.destroy();
}
			var ctx = document.getElementById('myChart'); 
			var myChart = new Chart(ctx, { 
					type: 'bar', 
					data: { 
						labels: keys,
						datasets: [{ 
							label: '시간대별 통계', 
							data: values, 
							backgroundColor: 'rgba(255, 99, 132, 0.2)',
							borderColor: 'rgba(255, 99, 132, 1)', 
							borderWidth: 1 }] }, 
						options: { 
							scales: { 
								xAxes: [{ ticks: { beginAtZero: true } }],
								yAxes: [{ ticks: { beginAtZero: true } }] 
								} 
						} 
					});

		}
	})
}

var myChart = null;
/*일별_바타입*/
function dateChart(){
	
	var year = $("#years").val();
	var month = $("#months").val();
	$.ajax({
		url : "/api/sales/dateChart",
		type : "POST",
			"headers":headers,
		data : {
			"year" : year,
			"month" : month,
		},
		success : function(res) {


			if(myChart != null){
				myChart.destroy();
			}
			var keys = [];
			var values = [];
			$.each(res, function(key, val) {
				keys.push(key.slice(-2));
				values.push(val);
			})
			
			/*차트---------------------------------------------*/
			var ctx = document.getElementById('myChart_date'); 
			myChart = new Chart(ctx, { 
					type: 'bar', 
					data: { 
						labels: keys,
						datasets: [{ 
							label: '일자별 통계', 
							data: values, 
							backgroundColor: 'rgba(54, 162, 235, 0.2)',
							borderColor: 'rgba(54, 162, 235, 1)', 
							borderWidth: 1 }] }, 
						options: { 
							scales: { 
								xAxes: [{ ticks: { beginAtZero: true } }],
								yAxes: [{ ticks: { beginAtZero: true } }] 
								} 
						} 
					});
			/*차트---------------------------------------------*/
		}
	})
}

var chart;
/*월별_바타입*/
function monthChart(){
	var year = $("#years2").val();
	$.ajax({
		url : "/api/sales/monthChart",
		type : "POST",
			"headers":headers,
		data : {
			"year" : year,
		},
		success : function(res) {
			
			if(chart != null)
			{
				chart.destroy();
			}
			
			var keys = [];
			var values = [];
			console.log(res);
			$.each(res, function(key, val) {
				keys.push(key.slice(-2));
				values.push(val);
			})
			
			/*차트---------------------------------------------*/
			var ctx = document.getElementById('myChart_month').getContext('2d'); 
			chart = new Chart(ctx, { 
				// 챠트 종류를 선택 
				type: 'line', 
				// 챠트를 그릴 데이타 
				data: { labels: keys, 
				datasets: [{ label: 'My First dataset', 
					backgroundColor: 'transparent', 
					borderColor: 'red', 
					data: values }] 
					}, // 옵션 
				options: {} 
				});

			/*차트---------------------------------------------*/
		}
	})
}

/*원형차트*/
function circleChart(date1,date2,aid){
	
	$.ajax({
		url : "/api/sales/circleChart",
		type : "POST",
			"headers":headers,
		data : {
			"date1" : date1,
			"date2" : date2
		},
		success : function(res) {

			var datas = [];
			var tot = 0;
			$.each(res, function(key, val) {
				datas.push(val);
				tot += val;
			})
			$(".two").find("h3").html("총 매출: "+numberToKorean(tot)+"원");

			data = { 
				datasets: [{ 
					backgroundColor: ['blue','red'], 
					data: datas }], 
				// 라벨의 이름이 툴팁처럼 마우스가 근처에 오면 나타남 
				labels: ["M:"+numberToKorean(datas[0]),"W:"+numberToKorean(datas[1])] 
			}; 
			// 가운데 구멍이 없는 파이형 차트 
			var ctx1 = document.getElementById("myChart1"); 
			var myPieChart = new Chart(ctx1, 
				{ 
				type: 'pie', 
				data: data, 
				options: {} 
			}); 

		}

	})
	
}
/*원형차트2*/
function circleChart2(aid){
	var year = $("#years").val();
	var month = $("#months").val();
	$.ajax({
		url : "/api/sales/circleChart2",
		type : "POST",
			"headers":headers,
		data : {
			"year" : year,
			"month" : month
		},
		success : function(res) {

			var datas = [];
			var tot = 0;
			$.each(res, function(key, val) {
				datas.push(val);
				tot += val;
			})
			$(".two").find("h3").html("총 매출: "+numberToKorean(tot)+"원");

			data = { 
				datasets: [{ 
					backgroundColor: ['blue','red'], 
					data: datas }], 
				// 라벨의 이름이 툴팁처럼 마우스가 근처에 오면 나타남 
				labels: ["M:"+numberToKorean(datas[0]),"W:"+numberToKorean(datas[1])] 
			}; 
			// 가운데 구멍이 없는 파이형 차트 
			
			var ctx1 = document.getElementById("myChart1_date"); 
			var myPieChart = new Chart(ctx1, 
				{ 
				type: 'pie', 
				data: data, 
				options: {} 
			}); 

		}

	})
	
}
/*원형차트3*/
function circleChart3(aid){
	var year = $("#years2").val();
	console.log(year);
	$.ajax({
		url : "/api/sales/circleChart3",
		type : "POST",
			"headers":headers,
		data : {
			"year" : year,
		},
		success : function(res) {

			var datas = [];
			var tot = 0;
			$.each(res, function(key, val) {
				datas.push(val);
				tot += val;
			})
			$("#"+aid).find(".two").find("h3").html("총 매출: "+numberToKorean(tot)+"원");

			data = { 
				datasets: [{ 
					backgroundColor: ['blue','red'], 
					data: datas }], 
				// 라벨의 이름이 툴팁처럼 마우스가 근처에 오면 나타남 
				labels: ["M:"+numberToKorean(datas[0]),"W:"+numberToKorean(datas[1])] 
			}; 
			// 가운데 구멍이 없는 파이형 차트 
			var ctx1 = document.getElementById("myChart1_month"); 
			var myPieChart = new Chart(ctx1, 
				{ 
				type: 'pie', 
				data: data, 
				options: {} 
			}); 

		}

	})
	
}

//화폐 숫자 -> 한국말 단위
function numberToKorean(number) {
		var inputNumber = number < 0 ? false : number;
		var unitWords = [ '', '만', '억', '조', '경' ];
		var splitUnit = 10000;
		var splitCount = unitWords.length;
		var resultArray = [];
		var resultString = '';

		for (var i = 0; i < splitCount; i++) {
			var unitResult = (inputNumber % Math.pow(splitUnit, i + 1))
					/ Math.pow(splitUnit, i);
			unitResult = Math.floor(unitResult);
			if (unitResult > 0) {
				resultArray[i] = unitResult;
			}
		}

		for (var i = 0; i < resultArray.length; i++) {
			if (!resultArray[i])
				continue;
			resultString = String(resultArray[i]) + unitWords[i] + resultString;
		}

		return resultString;
	}
	