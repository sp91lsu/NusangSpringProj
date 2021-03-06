
// 반응형 높이 설정
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
};
//서브밋 유효성검사
function submitChk(){
	var numTypes = $("input[type='number']");
	numTypes.each(function(){
		var t = $(this);
		var name = t.attr("name");
		var lastC = name.charAt(name.length-1);
		if(t.val()==""){
			if(lastC=="n"){
				t.val(0);
			}else{
				t.val(1000000000);
			}
		};
	});
	
	$("#valForm").submit();
}

// 캐럿 위아래 스위치

$(function(){
	setH();
	
	$('.caretBox').each(function(){
		$(this).click(function(){
			var th = $(this);
			var cd = th.children('i:first');
			var cu = cd.next();
			if(cu.css("display")=="none"){
				cd.css("display","none");
				cu.css("display","inline-block");
			}else{
				cd.css("display","inline-block");
				cu.css("display","none");
			}
		});
	});
	
	//검색버튼에 서브밋 유효성검사 함수 내장
	$("#searchBtn").click(function(){        
        submitChk(); 
    });

	var inputH = $("#searchBody input").height();
	$("#searchBody select").height(inputH+3.5);
	
	$(window).resize(function(){
		setH();
	});
});
