package com.mycom.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.model.Response;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.service.FriendService;
import com.mycom.blog.service.UserService;

@RestController
public class FriendController_API {

	@Autowired
	ConAssist conAssist;

	@Autowired
	private FriendService friendService;

	@Autowired
	private UserService userService;

	@PostMapping("/friend/add_friend")
	public int addFriend(int friendno, @AuthenticationPrincipal PrincipalDetail principal) {

		System.out.println("friendno : " + friendno);
		int result = friendService.addFriend(friendno, principal.getUser());
		return result;
	}

	@PostMapping("/friend/add_friend_req")
	public int add_friend_req(int friendno) {

		System.out.println("friendno : " + friendno);
		int result = friendService.addFriend_request(friendno);
		return result;
	}

}
