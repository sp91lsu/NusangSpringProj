package com.mycom.blog.controller.api;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.User;
import com.mycom.blog.model.Response;
import com.mycom.blog.repository.BoardRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.blog.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileApiController {
	
	@Autowired
	ConAssist conAssist;
	
	@Autowired
	ProfileService profileService;
	 
	@RequestMapping("/nickNameUpdate")
	public int update(String nickName) {
		System.out.println("닉네임 변경 컨트롤러 " + nickName);
		
		int res = profileService.nickNameUpdate(nickName);
		conAssist.updateUser();
		return res;
	}
	
	@GetMapping("/deletePicture")
	public int deletePic( HttpServletRequest request) {
		int res = profileService.deletePicture(request);
		User user =  conAssist.updateUser();
		//System.out.println(user.getBoardList().size());
		return res;
	}
	

}
