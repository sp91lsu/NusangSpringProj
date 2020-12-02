function setH(){
	var wH = window.innerHeight;
	var topH = $("#welcome").height();
	var barH = $("#bar").height();
	var sbtnH = $("#searchBtn").height();
	
	var con = $(".container");
	var sbody = $("#searchBody");
	
	$("body").height(topH+barH+wH);
	con.height(wH-topH);
	sbody.height(wH-topH-sbtnH-20);
}

$(function(){
	// 반응형 높이 설정
	setH();
	
	var inputH = $("#searchBody input").height();
	$("#searchBody select").height(inputH+3.5);
	
	$(window).resize(function(){
		setH();
	});
});
