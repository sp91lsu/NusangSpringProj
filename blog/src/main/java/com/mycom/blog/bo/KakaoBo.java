package com.mycom.blog.bo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mycom.blog.bo.assistance.EContentType;
import com.mycom.blog.bo.assistance.MyHttpGet;
import com.mycom.blog.bo.assistance.MyHttpPost;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.Location;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.GenderType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.model.KakaoProfile;
import com.mycom.blog.model.OAuthToken;
import com.mycom.blog.service.UserService;

@Service
public class KakaoBo extends BasicBO {

	@Value("${cos.key}")
	private String cosKey;

	OAuthToken oAuthToken = null;

	ConAssist conAssist;

	@Autowired
	private UserService userService;
	String code = null;

	@Autowired
	private KakaoBo(ConAssist conAssist) {
		
		this.conAssist = conAssist;
		Client_ID = "08d4e0306ac821705c50a3083bf85370";
		reqTokenURL = "https://kauth.kakao.com/oauth/token";
		redirectURL = conAssist.IP + "/user/kakaologin";
		reqUserInfoURL = "https://kapi.kakao.com/v2/user/me";
	}

	public User login(String code) {
		System.out.println(code);

		reqAuthToken(code);

		// 사용자 정보요청

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> kakaoUserReq = new HttpEntity<>(headers);

		ResponseEntity<String> response = rt.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoUserReq, String.class);

		KakaoProfile kProfile = null;

		System.out.println("바디바디 :" + response.getBody());
		boolean agreeMentChk = response.getBody().contains("needs_agreement\":true");
		
		
		if(agreeMentChk)
		{
			reqUnlink();
			return null;
		}
		
		try {
			
			kProfile = m.readValue(response.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(kProfile.getId());
		System.out.println(kProfile.getKakao_account().getEmail());

		kProfile.getKakao_account().getBirthday();
		
		User user = User.builder().userid(kProfile.getKakao_account().getEmail() + "_" + kProfile.getId())
				.username(kProfile.getProperties().getNickname()).nickname(kProfile.getProperties().getNickname())
				.email(kProfile.getKakao_account().getEmail()).password(cosKey)// UUID.randomUUID().toString()
				.build();
		
		// 회원가입
		System.out.println("유저 이름이 뭔데 : " + user.getUsername());
		System.out.println("유저 이름이 뭔데 : " + user.getPassword());
		System.out.println("유저 이름이 뭔데 : " + user.getEmail());

		if (!userService.isExsistUserName(user.getUsername())) {
			System.out.println("아이디가 존재 하지 않군요 가입해야겠어요");
			userService.signUp(user);
		}
		
		
		conAssist.setSessionUser(user);
		
		return user;
	}

	@Override
	public void reqAuthToken(String... code) {
		System.out.println(code[0]);

		// rest http 전송가능케하는 객체
		RestTemplate rt = new RestTemplate();

		// 헤더에 마임타입 정의
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// httpEntity가 multiValueMap을 포함하고있어서 선언 그냥 맵이랑 비슷하다
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

		// 카카오 문서에 적힌 내용을 기입
		params.add("grant_type", "authorization_code");
		params.add("client_id", Client_ID);
		params.add("redirect_uri", conAssist.IP + "/auth/kakaologin");
		params.add("code", code[0]);

		// 헤더와 바디를 합침
		HttpEntity<MultiValueMap<String, String>> kakaoTokenReq = new HttpEntity<MultiValueMap<String, String>>(params,
				headers);

		// 응답
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenReq, String.class);

		ObjectMapper objMapper = new ObjectMapper();

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
	}

	public void reqUnlink() {
		// 토큰 발급받을 수 있는 코드

		MyHttpPost httpPost = new MyHttpPost("https://kapi.kakao.com/v1/user/unlink", EContentType.FORM);
		Map<String, String> reqUserInfoMap = new HashMap<String, String>();
		reqUserInfoMap.put("Authorization", "Bearer " + oAuthToken.getAccess_token());
		httpPost.setHeader(reqUserInfoMap);

		JsonNode resObject = httpPost.request();

	}

	public User reqUserInfo() {
		// 사용자 정보 요청 token은 헤더에 담아서 보내야함
		MyHttpGet httpGet = new MyHttpGet(reqUserInfoURL, EContentType.FORM);

		Map<String, String> reqUserInfoMap = new HashMap<String, String>();
		reqUserInfoMap.put("Authorization", "Bearer " + oAuthToken.getAccess_token());
		httpGet.setHeader(reqUserInfoMap);

		JsonNode resNode = httpGet.request();

		System.out.println("사용자 정보 : " + resNode.toPrettyString());
		String id = resNode.get("id").asText();
		System.out.println(id);
		JsonNode accountNode = resNode.get("kakao_account");
		String email = "";
		User user = null;
		if (accountNode.get("email") != null) {

			email = accountNode.get("email").asText();
			System.out.println("email : " + email);
			JsonNode profileNode = accountNode.get("profile");

			String userId = "kakao_" + id;
			String name = profileNode.get("nickname").asText();
			user = User.builder().userid(userId).username(name).password(cosKey).email(email).nickname(name).build();
		}

		return user;
	}

	public Location reqLocation(double longtitude, double latitude) throws Exception {
		String url = "https://dapi.kakao.com/v2/local/geo/coord2address.json?x=" + longtitude + "&y=" + latitude
				+ "&input_coord=WGS84";

		MyHttpGet httpGet = new MyHttpGet(url, EContentType.FORM);

		Map<String, String> reqUserInfoMap = new HashMap<String, String>();
		reqUserInfoMap.put("Authorization", "KakaoAK " + Client_ID);

		httpGet.setHeader(reqUserInfoMap);

		JsonNode resNode = httpGet.request();

		Location location = JsonToLocation(resNode);
		location.setLongtitude(longtitude);
		location.setLatitude(latitude);
		return location;
	}

	public JsonNode reqLocationList(String searchName) {
		System.out.println(searchName);

		String encodeSearchName = "";
		try {
			encodeSearchName = URLEncoder.encode(searchName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + encodeSearchName;
		MyHttpGet httpGet = new MyHttpGet(url, EContentType.FORM);

		Map<String, String> reqUserInfoMap = new HashMap<String, String>();
		reqUserInfoMap.put("Authorization", "KakaoAK " + Client_ID);

		httpGet.setHeader(reqUserInfoMap);

		JsonNode resNode = httpGet.request();

		// System.out.println("위치 정보 : " + resNode.toPrettyString());

		return resNode;
	}

	// 지번으로 검색하기
	public Location reqLocation(String locationName) throws Exception {
		JsonNode resNode = reqLocationList(locationName);
		return JsonToLocation(resNode);
	}

	private Location JsonToLocation(JsonNode resNode) throws Exception {

		ArrayNode documentsNode = m.createArrayNode();

		documentsNode = (ArrayNode) resNode.get("documents");
		// System.out.println("위치 정보 : " + documentsNode.toPrettyString());
		JsonNode addressNode = documentsNode.get(0).get("address");
		Location location = new Location();

		String[] addArr = addressNode.get("address_name").asText().split(" ");

		if (addArr.length >= 3) {
			location.setName1(addArr[0]);
			location.setName2(addArr[1]);
			location.setName3(addArr[2]);
		}

		if (addressNode.get("x") != null) {
			double x = Double.parseDouble(addressNode.get("x").asText());
			location.setLongtitude(x);
		}
		if (addressNode.get("y") != null) {
			double y = Double.parseDouble(addressNode.get("y").asText());
			location.setLatitude(y);
		}

		System.out.println("변환할 위치 : " + location.getAddress());
		return location;
	}
}
