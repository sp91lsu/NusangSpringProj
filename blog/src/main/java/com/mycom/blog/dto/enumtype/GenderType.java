package com.mycom.blog.dto.enumtype;

public enum GenderType {

	MALE("남"), FEMALE("여");

	String name;

	GenderType(String genderName) {
		name = genderName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
