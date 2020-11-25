(function() {

	
	$(".paging_input").click(
			function() {

				

				$.ajax({

					url : "/api/paging/?page="
							+ ($(this).html() -1),
					success : function(res) {
						
						$(".community_list").empty();
						res.content.forEach(element => {
						
						var post = "<div class='post' onclick=\"location.href='/post/post_read/"+element.id+"'\">" +
						'<div class="img"><img src=/image/panda.jpg></div>' +
						'<div class="txt">' +
						'<div class="writer">'+element.title+'</div>' +
						'<div class="comment">'+element.content+'</div>' +
						'<div class="view"><span>추천수0</span><span>조회수:0</span></div>' +
								"</div>"+"</div>"
								
						$(".community_list").append(post)
						console.log(element)
						});
					}
				})
			})
})()