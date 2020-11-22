package com.mycom.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@SessionAttributes("id") //id라는 키로 저장된 attribute는 세션객체에 저장 됨

@Controller
@RequestMapping(value = "friend")
public class FriendController {

	@GetMapping("/friends_view")
	public String friendView() {

		System.out.println("friends");
		return "friend/friends";
	}

	@GetMapping("/friends_request_view")
	public String friend_request_view() {
		System.out.println("friends_request_view");
		return "friend/friends_request";
	}

}
