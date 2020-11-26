var csrfHeader = $("meta[name='_csrf_header']").attr("content");
var csrfToken = $("meta[name='_csrf']").attr("content");

var headers = {};
headers[csrfHeader] = csrfToken;

function sendNum(number) {
	var result = confirm("검색 반경을 " + number + "km 로 설정하시겠습니까?");
	headers[csrfHeader] = csrfToken;

	console.log(headers)
	if (result) {

		$.ajax({

			url : "/location/set_distance",
			type : "POST",
			data : {
				"view_distance" : number
			},
			"headers" : headers,
			success : function(res) {

				if (res == "1") {
					location.reload();
				} else {
					alert("먼저 위치설정을 해주세요.");
				}
			}

		})

	} else {

	}

}