(function() {

	
	$(".paging_input").click(
			function() {

				

				$.ajax({

					url : "/manager/FAQ/api/faqList/?page="
							+ ($(this).html() -1),
					success : function(res) {
						
						$(".faq_list").empty();
						res.content.forEach(element => {
						
						var post = '<tr>'+
						'<td align = "center">'+
						'${dto.no }' +
						'</td>' +
						'<td>' +
						'<div class="titleColor">'+element.title+'</div>'+
						'<div class = "contents"><br>'+element.contents+'<br>'+
							'<button type = "button" class = "btn btn-primary btn-sm"'+
							'onclick="'+'location.href'+ '= "/manager/FAQ/faqUpdate?no='+element.no+'">수정</button>' +
							'<input type="hidden" name="no" value="${dto.no }" />'+
							'<button type = "submit" class = "btn btn-secondary btn-sm">삭제</button>' +
						'</div>' +
						'</td>' +
					'</tr>'
								
						$(".faq_list").append(post)
						console.log(element)
						});
					}
				})
			})
})()