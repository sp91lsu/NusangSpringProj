package com.mycom.blog.auth.provider;


public interface OAuth2UserInfo {
	String getProviderId();
	String getProvier();
	String getEmail();
	String getName();
}
