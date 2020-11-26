(function() {

	
	$(".paging_input").click(
			function() {

				

				$.ajax({

					url : "/manager/notice/api/noticeList/?page="
							+ ($(this).html() -1),
					success : function(res) {
						
						$(".notice_list").empty();
						res.content.forEach(element => {
						
						var post = '<tr>' +
						'<td><a style="color: black;"' +
							'href="/manager/notice/noticeView?no='+element.no+'">'+element.title+'</a></td>' +
						'<td align="center"> ' +element.regdate+ '</td>' +
					'</tr>'
								
						$(".notice_list").append(post)
						console.log(element)
						});
					}
				})
			})
})()