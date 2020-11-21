package com.mycom.blog.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.model.KakaoProfile;
import com.mycom.blog.model.OAuthToken;
import com.mycom.blog.service.UserService;

@Component
public class KakaoBo {

	@Value("${cos.key}")
	private String cosKey;

	@Autowired
	ConAssist conAssist;
	
	@Autowired
	private UserService userService;

	public void login(String code)
	{
		System.out.println(code);

		// rest http 전송가능케하는 객체
		RestTemplate rt = new RestTemplate();

		// 헤더에 마임타입 정의
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// httpEntity가 multiValueMap을 포함하고있어서 선언 그냥 맵이랑 비슷하다
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

		// 카카오 문서에 적힌 내용을 기입
		params.add("grant_type", "authorization_code");
		params.add("client_id", "08d4e0306ac821705c50a3083bf85370");
		params.add("redirect_uri", "http://localhost:8000/auth/kakaologin");
		params.add("code", code);

		// 헤더와 바디를 합침
		HttpEntity<MultiValueMap<String, String>> kakaoTokenReq = new HttpEntity<MultiValueMap<String, String>>(params,
				headers);

		// 응답
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenReq, String.class);

		ObjectMapper objMapper = new ObjectMapper();

		OAuthToken oAuthToken = null;

		try {
			oAuthToken = objMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("mapper accessToken : " + oAuthToken.getAccess_token());

		// 사용자 정보요청

		rt = new RestTemplate();
		headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> kakaoUserReq = new HttpEntity<>(headers);

		response = rt.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoUserReq, String.class);

		objMapper = new ObjectMapper();

		KakaoProfile kProfile = null;

		try {
			kProfile = objMapper.readValue(response.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(kProfile.getId());
		System.out.println(kProfile.getKakao_account().getEmail());

		User user = User.builder().userid(kProfile.getKakao_account().getEmail() + "_" + kProfile.getId())
				.username(kProfile.getProperties().getNickname())
				.nickname(kProfile.getProperties().getNickname())
				.email(kProfile.getKakao_account().getEmail()).password(cosKey)// UUID.randomUUID().toString()
				.build();

		// 회원가입
		System.out.println("유저 이름이 뭔데 : " + user.getUsername());
		System.out.println("유저 이름이 뭔데 : " + user.getPassword());
		System.out.println("유저 이름이 뭔데 : " + user.getEmail());

		if (!userService.isExsistUserName(user.getUsername())) {
			System.out.println("아이디가 존재 하지 않군요 가입해야겠어요");
			userService.signUp(user,AuthType.KAKAO);
		}

		conAssist.setSessionUser(user);
	}
}
