package com.mycom.blog.controller.api;

import java.io.Console;

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

import com.fasterxml.jackson.databind.JsonNode;
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
import com.mycom.blog.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocApiController {

	private final KakaoBo kakaoBo;

	private final ConAssist conAssist;
	@Autowired
	LocationService locationService;

	@GetMapping("/search_location")
	public String search_location(String searchValue) {

		System.out.println("search_location");
		JsonNode value = kakaoBo.reqLocationList(searchValue);
		return value.toString();
	}

	@PostMapping("/set_location")
	public int set_location(String searchValue) {

		System.out.println("set_location");

		int result = locationService.setLocation(searchValue);
		User user = conAssist.updateUser();
		System.out.println("수정된 유저 로케이션 " + user.getLocation());
		return result;
	}

	@PostMapping("/set_distance")
	public int set_distance(int view_distance) {
		int result = locationService.setView_distance(view_distance);
		conAssist.updateUser();
		return result;
	}
	
	
	
	
	
	
	
}
