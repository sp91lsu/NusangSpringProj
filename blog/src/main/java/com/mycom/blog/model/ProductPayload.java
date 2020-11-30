package com.mycom.blog.model;

import lombok.Data;

@Data
public class ProductPayload {
	int itemno;
	int userno;
	String merchant_uid;
	String imp_uid;
	String paid_amount;
}
