package com.mycom.blog.auth.provider;

import java.util.Map;

import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.model.KakaoProfile;
import com.mycom.blog.repository.UserRepository;
import com.mycom.blog.service.UserService;
import com.mycom.jooq.tables.JUser1;

import lombok.Data;

@Data
@Service
public class ProviderUserService {

	@Value("${cos.key}")
	private String cosKey;

	@Autowired
	private ConAssist conAssist;
	
	@Autowired
	private UserService userService;

	public User of(String registrationId, Map<String, Object> attributes) {

		System.out.println(attributes);
		User user = null;
		if ("naver".equals(registrationId)) {
			user = ofNaver(attributes);
		} else if ("kakao".equals(registrationId)) {
			user = ofKakao(attributes);
		} else if ("facebook".equals(registrationId)) {
			user = ofFaceBook(attributes);
		} else if ("google".equals(registrationId)) {
			user = ofGoogle(attributes);
		}

		user.setPassword(cosKey);
		user.setRole(RoleType.USER);

		User updateUser = userService.findUserId(user.getUserid());
		if (updateUser == null) {
			
			userService.signUp(user);
			System.out.println(user.getNickname()); 
			updateUser = userService.searchNickname(user.getNickname());
			System.out.println("해당 유저 가입" + updateUser.getUserno());
		}
		return updateUser;
	}

	private User ofNaver(Map<String, Object> attributes) {

		attributes = (Map<String, Object>) attributes.get("response");
		String username = (String) attributes.get("name");
		String signature = username + (String) attributes.get("id");
		return User.builder().nickname(username).userid(signature).username(username)
				.email((String) attributes.get("email")).authType(AuthType.NAVER)

				.build();
	}

	private User ofKakao(Map<String, Object> attributes) {

		
		Integer id = (Integer) attributes.get("id"); 
		Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
		Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");
		String name = (String) profile.get("nickname");
		String email = (String) kakao_account.get("email");
				
		String signiture = name + "_" + id;
		
		return User.builder().nickname(name).userid(signiture).username(name)
				.email(email).authType(AuthType.KAKAO)

				.build();
	}

	private User ofGoogle(Map<String, Object> attributes) {
		String username = (String) attributes.get("name");
		String signiture = username + "_" + (String) attributes.get("sub");
		return User.builder().nickname(username).userid(signiture).username(username)
				.picture((String) attributes.get("picture")).email((String) attributes.get("email"))
				.authType(AuthType.GOOGLE)

				.build();
	}

	private User ofFaceBook(Map<String, Object> attributes) {
		String name = (String) attributes.get("name");
		String email = (String) attributes.get("email");
		String id = (String) attributes.get("id");

		String signiture = name + "_" + id;

		return User.builder().nickname(name).userid(signiture).username(name).email(email)
				.authType(AuthType.FACEBOOK)

				.build();
	}

}
