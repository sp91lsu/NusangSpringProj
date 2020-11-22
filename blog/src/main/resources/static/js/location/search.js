(function() {

	$("#searchBtn")
			.click(
					function() {

						$("#searchTable").empty();

						$
								.ajax({
									url : "/location/search_location?searchValue="
											+ $("#searchText").val(),

									success : function(data) {

										$
												.each(
														JSON.parse(data),
														function(key, documents) {

															if (key == 'documents') {

																$
																		.each(
																				documents,
																				function(
																						key,
																						value) {

																					let
																					address = value.address;
																					var jbSplit = address.address_name
																							.split(' ');
																					if (jbSplit.length >= 3) {
																						$(
																								"#searchTable")
																								.append(
																										"<tbody class='searchTab'><tr><td>"
																												+ address.address_name
																												+ "</td></tr></tbody>");
																					}
																				});

															}
														});

										$('.searchTab').click(function() {

											setLocation(this);
										});
									},
									error : function(request, status, error) {
										alert(request + status + error);
									}
								});
					});

	$('#searchText').keypress(function(event) {
		if (event.which == 13) {
			$('#searchBtn').click();
			return false;
		}
	});

	function setLocation(searchTab) {

		let
		locName = $(searchTab).children("tr").children("td").text();
		let
		modal = $('#modal_loc');

		$(modal).modal("show");

		let
		modalY = $('#modal_loc #modalY');
		console.log(locName)
		$(modalY).click(function(e) {
			$(modal).modal('hide').data('bs.modal', null);
			// 여기에서 예를 눌렀을 떄 수행해야함

			$.ajax({
				url : "/location/set_location",
				data : {

					"searchValue" : locName
				},
				type : "POST",
				success : function(data) {
					if (data.charAt(0) == '/') {
						location.href = data;
					} else {
						alert(data);
						location.reload();
					}
				}
			})

		})

	}

})()