package com.mycom.blog.model;

import lombok.Data;

@Data
public class ImportRequest {

	private String imp_uid;
	private String merchant_uid;
	private String status;
}
