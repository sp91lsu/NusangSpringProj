package com.mycom.blog.controller.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycom.blog.dto.User;
import com.mycom.blog.dto.manager.SearchVal;
import com.mycom.blog.service.UserService;


@Controller
@RequestMapping("/manager/member/")
public class SearchController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("search")
	public void search(Model model) {
		List<User> userList = userService.findAll_ASCUserno();
		model.addAttribute("list",userList);
	}
	
	@GetMapping("searchValues")
	public String searchV(Model model,SearchVal sv) {
//		List<User> userList = userService.findBySearchValues(sv);
		model.addAttribute("nomin",sv.getNo_min());
		model.addAttribute("nomax",sv.getNo_max());
		model.addAttribute("name",sv.getName());
		model.addAttribute("gen",sv.getGender());
		return "/manager/member/test";
	}
	
	@GetMapping("searched")
	public String searchedList(@RequestParam(required = false) Map<String, Object> searchRequest, Model model) {
		System.out.println("서치드-----------"+searchRequest.get("username"));
		List<User> list = userService.searchedList(searchRequest);
		model.addAttribute("list",list);
	    return "/manager/member/search";
	} 
	
}
