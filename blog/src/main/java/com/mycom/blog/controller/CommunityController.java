package com.mycom.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/community")
public class CommunityController {
	
	@RequestMapping(value = "/list")
	public String list() {
		return "client/community/list";
	}
}
