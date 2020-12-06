/*$("#qnaBtn").click(function(){
		var qnaTitle = $("#qnaTitle").val().trim();
		var qnaContents = $("#qnaContents").val();
		qnaContents = qnaContents.replace(/<p>/g,'');
		qnaContents = qnaContents.replace(/<\/p>/g,'');
		qnaContents = qnaContents.replace(/<br>/g,'');
		qnaContents = qnaContents.replace(/&nbsp;/g,'');
		qnaContents = qnaContents.trim(); 
		//alert("내용 : " + contents);
		if((qnaTitle == "") || (qnaContents == "")){
			alert("제목과 내용을 모두 작성해주세요");
		}else{
			${"qnaWrite"}.submit();
		}
	})
		// html dom 이 다 로딩된 후 실행된다.
		$(document).ready(function() {
			// menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
			$(".titleColor").click(function() {
				var submenu = $(this).parent().parent().next("tr");

				// submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
				if (submenu.is(":visible")) {
					submenu.slideUp();
				} else {
					submenu.slideDown();
				}
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
		});*/