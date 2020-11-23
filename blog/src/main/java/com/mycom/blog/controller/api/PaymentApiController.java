package com.mycom.blog.controller.api;

import java.io.Console;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.bo.KakaoBo;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.dto.Location;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.model.Response;
import com.mycom.blog.repository.BoardRepository;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.repository.ReplyRepository;
import com.mycom.blog.service.LocationService;
import com.mycom.blog.service.ShopService;
import com.mycom.blog.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
public class PaymentApiController {

	@Autowired
	ShopService shopService;

	@Autowired
	UserService userService;

	@PostMapping("/webhook")
	public void search_location(HttpServletRequest request) throws IOException {

		System.out.println(request.getInputStream());
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = mapper.readValue(request.getInputStream(), Map.class);

		String status = jsonMap.get("status").toString();

		if (status.equals("paid")) {
			userService.buyCoin(jsonMap);
		}
	}

}
