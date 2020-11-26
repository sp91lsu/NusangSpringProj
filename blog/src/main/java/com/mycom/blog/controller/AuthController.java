package com.mycom.blog.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycom.blog.bo.KakaoBo;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.model.KakaoProfile;
import com.mycom.blog.model.OAuthToken;
import com.mycom.blog.service.UserService;

//@SessionAttributes("id") //id라는 키로 저장된 attribute는 세션객체에 저장 됨

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

	// yml파일에 있는 키값
	@Value("${cos.key}")
	private String cosKey;

	@Autowired
	private UserService userService;

	
	@Autowired
	ConAssist conAssist;
	
	@Autowired
	private KakaoBo kakaoBo;

	@GetMapping("/joinForm")
	public String joinForm() {

		System.out.println("joinForm");
		return "user/joinForm";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		System.out.println("loginForm");
		return "user/loginForm";
	}

	// @ResponseBody : 데이터를 리턴해주는 함수로 바뀜 restController같은 역할
	@GetMapping("/kakaologin")
	public String kakaoCallback(String code, Model model) {

		User user = kakaoBo.login(code);
		
		if (user == null) {
			model.addAttribute("error", "다시 로그인하여 카카오 추가 동의를 해주세요.");
			return "/layout/error";
		} else {
			
			user = conAssist.updateUser();
			if (user.getRole() == RoleType.USER) {
				return "redirect:/home";
			} else {
				return "redirect:/manager/notice/noticeList";
			}
		}

	}

}
