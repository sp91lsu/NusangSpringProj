package com.mycom.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class managerController {
	@RequestMapping("/noticeList")
	public String noticeList(){
		return "/manager/noticeList";
	}
	@RequestMapping("/noticeWrite")
	public String noticeWrite() {
		return "/manager/noticeWrite";
	}
}
