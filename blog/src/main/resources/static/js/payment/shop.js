(function() {
	var IMP = window.IMP; // 생략가능
	IMP.init('imp11398251'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용

	// createPost 유효성 검사
	$(".product_item").click(function() {

		buyProduct(this);
	});

	var modalY = $('#modal_buy_item #modalY');

	$(modalY).click(function(e) {
		// 게시판 bodytext(내용) 엔터처리
		location.reload();
	});

	function buyProduct(me) {

		IMP.request_pay({
			pg : 'inicis', // version 1.1.0부터 지원.
			pay_method : 'card',
			merchant_uid : $("#user_no").val() + "_"
					+ $(me).find("#itemno").val() + "_" + uuidv4(),
			name : '상품 구매',
			amount : 100,
			buyer_email : $("#user_email").val(),
			buyer_name : '',
			buyer_tel : '010-1234-5678',
			buyer_addr : '서울특별시 강남구 삼성동',
			buyer_postcode : '123-456',
			m_redirect_url : 'https://www.yourdomain.com/payments/complete'
		}, function(rsp) {
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += '상점 거래ID : ' + rsp.merchant_uid;
				msg += '결제 금액 : ' + rsp.paid_amount;
				msg += '카드 승인번호 : ' + rsp.apply_num;
				let
				modal = $('#modal_buy_item');

				var prouductObject = {
					userno : $("#user_no").val(),
					itemno : $(me).find("#itemno").val(),
					merchant_uid : rsp.merchant_uid,
					imp_uid : rsp.imp_uid,
					paid_amount : rsp.paid_amount
				}

				$.ajax({

					url : "/payment/buy_product",
					type : "POST",
					headers: headers,
					data : prouductObject,
					success : function(res) {
						if (res == 1) {
							$(modal).modal("show");
						} else {
							alert("결제에 실패하였습니다. 관리자에게 문의해주세요.")
						}

					}

				})

			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
				alert("결제에 실패하였습니다. 관리자에게 문의해주세요.")
			}

		});

	}

	// 랜덤 uuid생성
	function uuidv4() {
		return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,
				function(c) {
					var r = Math.random() * 16 | 0, v = c == 'x' ? r
							: (r & 0x3 | 0x8);
					return v.toString(16);
				});
	}

	// 환불
	function cancelPay() {

		console.log("환불하기");
		jQuery.ajax({
			"url" : "/payment/refund",
			"type" : "POST",
			"contentType" : "application/json",
			"charset" : "utf-8",
			"data" : JSON.stringify({
				"merchant_uid" : "merchant_1602958478736", // 주문번호
				"cancel_request_amount" : 100, // 환불금액
				"reason" : "결제 환불", // 환불사유
			// "refund_holder" : "홍길동", // [가상계좌 환불시 필수입력] 환불 수령계좌 예금주
			// "refund_bank" : "88", // [가상계좌 환불시 필수입력] 환불 수령계좌 은행코드(ex.
			// // KG이니시스의
			// // 경우 신한은행은 88번)
			// "refund_account" : "56211105948400", // [가상계좌 환불시 필수입력] 환불
			// 수령계좌
			// 번호
			}),
			"dataType" : "json",
			success : function(data) {
				// 서버로부터 정상적으로 응답이 왔을 때 실행
				console.log("응답 성공 " + data);
			},
			error : function(err) {
				// 서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
				console.log("응답 실패 " + err);
			}

		});
	}

	/*
	 * var cancelBtn = document.getElementById("cancelPay");
	 * console.log("환불버튼"); cancelBtn.addEventListener("click", cancelPay);
	 */

})();

/* 이미지추가 관련 스크립트 */
var i = 0;
var deleteBtn = $("<button type='button' style = 'margin-top:-3px;' class = 'btn btn-danger btn-sm' id='deleteBtn' onclick='cntdown(this)'>삭제</button>")
var btnChk;
var isSet;
var imgDiv;

$("#btnAdd")
		.click(
				function() {

					imgDiv = $("<div><input type='file' style = 'background:#BDBDBD' id='upfile"
							+ i + "' name='upfile" + i + "'/></div>")

					btnChk = document.getElementById('upfile' + (i - 1)); // 0
					if (i == 0) {
						$("#files").append(imgDiv);
						imgDiv.append(deleteBtn);
						i++;
					} else if (i < 5 && i != 0 && btnChk.value != "") {

						deleteBtn = $(deleteBtn).detach();
						$("#files").append(imgDiv);
						imgDiv.append(deleteBtn);
						i++;
					} else {
						btnChk = document.getElementById('upfile' + (i - 2)); // 0
					}

					console.log('현제:' + i);
				});
function cntdown(me) {
	$(me).parent().remove();

	i--;
	console.log('현제:' + i);
	btnChk = document.getElementById('upfile' + (i - 1))
	console.log("추가해야 하는 삭제 버튼 위치" + (i - 1))
	$(btnChk).closest("div").append(deleteBtn)

}