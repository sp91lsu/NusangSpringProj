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
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.model.KakaoProfile;
import com.mycom.blog.model.OAuthToken;
import com.mycom.blog.service.UserService;

//@SessionAttributes("id") //id라는 키로 저장된 attribute는 세션객체에 저장 됨

@Controller
@RequestMapping(value = { "/user" })
public class UserController {

	// yml파일에 있는 키값
	@Value("${cos.key}")
	private String cosKey;

	@Autowired
	private UserService userService;

	@GetMapping("/updateForm")
	public String updateForm() {
		System.out.println("updateForm");
		return "/user/updateForm";
	}

	@GetMapping("/search")
	public String userSearch() {
		return "/user/searchUser";
	}

	@GetMapping("/near_userlist")
	public String near_userlist(Model model) {
		List<User> nearUserList = userService.findNearUserList();
		model.addAttribute("nearUserList", nearUserList);
		return "/user/near_userlist";
	}

	@GetMapping("/search_location")
	public String userSearch_location() {
		return "/location/search";
	}

	@GetMapping("/all_userlist")
	public String allUserList(Model model) {
		List<User> userlist = userService.findAll();
		model.addAttribute("allUserList", userlist);
		return "/user/allUserList";
	}

}
