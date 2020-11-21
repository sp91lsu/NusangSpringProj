package com.mycom.blog.bo.assistance;


import lombok.Getter;

@Getter
public enum EContentType {
	JSON("application/json"), FORM("application/x-www-form-urlencoded");

	String text;

	EContentType(String text) {
		this.text = text + ";charset=utf-8";
	}
}