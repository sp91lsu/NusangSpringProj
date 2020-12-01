(function() {

	
	$(".qna_paging").click(
			function() {

				

				$.ajax({

					url : "/manager/QNA/api/qnaList/?page="
							+ ($(this).html() -1),
					success : function(res) {
						
						$(".qna_list").empty();
						res.content.forEach(element => {
						if(element.answer == null)
							{
							element.answer ="";
							}
						var post =
						
						'<tr>'+
						'<td align = "center">'+ element.no + '</td>' +
						'<td>' +
						'<div class="titleColor">'+element.title+'</div>'+
						'</td>' +
						'<td>'+element.regdate+'</td>' +
						'</tr>' +
						'<tr class = "contents">' +
						'<td></td>' +
						'<td  colspan="2"><br>' +element.contents + "<br>" +
						'<textarea id = "answer" name = "answer" class = "textarea" cols="52" rows="7" placeholder="답변달기">' + element.answer +'</textarea>' +
						'<button type="submit" class = "answer btn btn-primary btn-sm">답변달기</button>' +
						'<input type="hidden" id="answerno" value="' + element.no + '" />' +
						'</td>' +
						'</tr>' 
					
						
						$(".qna_list").append(post)
						
						
						console.log(element.answer)
						});
						
						
						$(".answer").click(function() {
							
							var text =  $(this).parent().children("#answer").val();
							var no  = $(this).parent().children("#answerno").val();
							console.log("answer" + text);
							$.ajax({
								
								url: "/manager/QNA/api/qnaUpdateOk",
								type: "POST",
								headers: headers,
								data: {
									no: no,
									answer:text
								},
								success: function(res){
									console.log(res)
									if(res == 1)
									{alert("수정되ㅑ었습니다")
									location.reload();	
									}
									}
								
							})
						})	
						 $(".titleColor").click(function(){
					            var submenu = $(this).parent().parent().next("tr");
					 
					            // submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게
								// 펼치기
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