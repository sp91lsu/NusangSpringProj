(function() {

	
	$(".faq_paging").click(
			function() {

				

				$.ajax({

					url : "/manager/FAQ/api/faqList/?page="
							+ ($(this).html() -1),
					success : function(res) {
						
						$(".faq_list").empty();
						res.content.forEach(element => {
						
						var post = '<tr>'+
						'<td align = "center">'+
						element.no +
						'</td>' +
						'<td>' +
						'<div class="titleColor">'+element.title+'</div>'+
						'<div class = "contents"><br>'+element.contents+'<br>';
						if(user.role == 'ADMIN'){
							post += 
								'<button style = "margin-right:3px;" type = "button" class = "btn btn-primary btn-sm deleteBtn"'+
								'onclick="location.href=\'/manager/FAQ/faqUpdate?no='+element.no+'\'">수정</button>' +
								'<input type="hidden"'+' name="no"'+' value="'+element.no +'" />'+
								'<button type = "submit" class = "btn btn-secondary btn-sm updateBtn">삭제</button>';
						};
						post +=
						'</div>' +
						'</td>' +
					'</tr>'
								
						$(".faq_list").append(post)
						console.log(element)
						});
						  $(".titleColor").click(function(){
					            var submenu = $(this).next("div");
					 
					            // submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
					            if( submenu.is(":visible") ){
					                submenu.slideUp();
					            }else{
					                submenu.slideDown();
					            }
					        });
					}
				})
			})
})()