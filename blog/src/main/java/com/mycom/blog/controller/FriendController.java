package com.mycom.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@SessionAttributes("id") //id라는 키로 저장된 attribute는 세션객체에 저장 됨

@Controller
public class FriendController {

	@GetMapping("/friend/friends_view")
	public String friendView() {

		System.out.println("friends");
		return "friend/friends";
	}
	
}
