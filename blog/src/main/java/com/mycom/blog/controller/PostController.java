package com.mycom.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/post")
public class PostController {
	
	@RequestMapping(value = "/post_write")
	public String write() {
		return "/post/post_write";
	}
}
