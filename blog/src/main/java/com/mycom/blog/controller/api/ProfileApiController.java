package com.mycom.blog.controller.api;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.GenderType;
import com.mycom.blog.model.Response;
import com.mycom.blog.repository.BoardRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.blog.service.ProfileService;
import com.mycom.blog.vo.BoardVO;

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
	
	@PostMapping("/genderSelect")
	public int updateGender(@RequestParam GenderType gender) {
		int res = profileService.updateGender(gender);
		conAssist.updateUser();
		return res;
	}
	
	@PostMapping("/ageSelect")
	public int updateGender(int age) {
		int res = profileService.updateAge(age);
		conAssist.updateUser();
		return res;
	}
	
	@RequestMapping("/updatePost")
	public Page updatePost(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
			User userno) {
		System.out.println("유저넘 뭔데?? "+userno);
		Page<BoardVO> boardList = profileService.getPageList(pageable, userno);
		System.out.println("boardList 출력:  " + boardList.getContent());
		model.addAttribute("myBoardList", boardList);
		 
		conAssist.updateUser();
		return boardList;
	}
	

}
