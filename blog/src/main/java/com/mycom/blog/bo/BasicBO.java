package com.mycom.blog.bo;


import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

//소셜 api사용을 위해 만듦
@Data
public abstract class BasicBO {
	String tokenURL = null;
	String Client_ID = null;
	String reqTokenURL = null;
	String redirectURL = null;
	String reqUserInfoURL = null;
	String code = null;
	protected ObjectMapper m = new ObjectMapper();

	public abstract void reqAuthToken(String... code);

}
