package com.mycom.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.User;
import com.mycom.blog.service.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	ConAssist conAssist;
	
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping("/profileMain")
	String profile() {
		return "profile/profileMain";
	}
	
	@PostMapping(value =  "/updatePicture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String update(@RequestParam("file") MultipartFile file, HttpServletRequest rquest){
		profileService.updatePicture(file, rquest);
		conAssist.updateUser();
		return "profile/profileMain";
	}
	
		
}
