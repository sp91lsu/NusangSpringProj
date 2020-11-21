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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.model.Response;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.service.FriendService;
import com.mycom.blog.service.UserService;

@RestController
public class FriendApiController {

	@Autowired
	private FriendService friendService;

	@Autowired
	private UserService userService;

	@PostMapping("/friend/add_friend")
	public Response<Integer> addFriend(int friendno,@AuthenticationPrincipal PrincipalDetail principal) {

		System.out.println("friendno : " + friendno);
		friendService.addFriend(friendno, principal.getUser());
		return new Response<Integer>(HttpStatus.OK.value(), 0);
	}

}
