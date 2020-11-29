package com.mycom.blog.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.mycom.blog.auth.provider.ProviderUserService;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private ProviderUserService providerUserInfo;
	
	// 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration : " + userRequest.getClientRegistration()); // 여기서 어떤 oauth로 로그인 해쓴지
																								// 확인 가능
		System.out.println("getAccessToken : " + userRequest.getAccessToken());

		OAuth2User auth2User = super.loadUser(userRequest);
		System.out.println("loadUser : " + auth2User.getAttributes());

		String provider = userRequest.getClientRegistration().getRegistrationId();

		return new PrincipalDetail(providerUserInfo.of(provider, auth2User.getAttributes()));
	}
}
