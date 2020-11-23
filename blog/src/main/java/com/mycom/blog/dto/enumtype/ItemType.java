package com.mycom.blog.dto.enumtype;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ItemType {

	COIN("cm");

	private String name;

	ItemType(String name) {
		this.name = name;
	}
}
