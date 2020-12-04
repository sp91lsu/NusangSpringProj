var paging = {
	init : function() {
		$(".paging_num").click(function(){
			var paging_num = $(this).text()-1;
			console.log($(this).text());
			location.href="/home?page=" +paging_num
		})
	}
}
paging.init();