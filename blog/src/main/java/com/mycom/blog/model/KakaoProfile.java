package com.mycom.blog.model;

import lombok.Data;

@Data
public class KakaoProfile {

	public Integer id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;

	@Data
	public class KakaoAccount {

		private Boolean profile_needs_agreement;
		private Profile profile ;
		private Boolean has_email;
		private Boolean email_needs_agreement;
		private Boolean is_email_valid;
		private Boolean is_email_verified;
		private String email;
	}

	@Data
	public class Properties {

		private String nickname;
		private String profile_image;
		private String thumbnail_image;
	}

	@Data
	public static class Profile {

		private String nickname;
		private String thumbnail_image_url;
		private String profile_image_url;
		
	}

}
