package com.mycom.blog.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent.Kind;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.GenderType;
import com.mycom.blog.repository.BoardRepository;
import com.mycom.blog.repository.UserRepository;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class ProfileService {

	@Autowired
	MultipartResolver multiResolver;

	@Autowired
	ConAssist conAssist;

	@Autowired
	UserRepository userRepository;

	// 사진 업데이트
	@Transactional
	public int updatePicture(MultipartFile file, HttpServletRequest request) {

		String savePath = request.getSession().getServletContext().getRealPath("");
		savePath = savePath.replace("webapp", "resources/static/upload");
		System.out.println("업로드 경로:  " + savePath);
		// savePath = "C:\\";
		Date current = new Date();
		String fileName = current.getTime() + "_" + file.getOriginalFilename();

		try {
			User user = userRepository.findById(conAssist.getUserno()).get();

			// 이전 사진파일 지우기
			File f = new File(savePath + user.getPicture());
			if (f.exists() && !user.getPicture().equals("profileImg.jpg")) {
				f.delete();
			}

			File newFile = new File(savePath + fileName);
			file.transferTo(newFile);

			user.setPicture("/upload/"+fileName);

			return 1;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;

	}

	// 사진 지우기(기본사진으로)
	@Transactional
	public int deletePicture(HttpServletRequest request) {
		String savePath = request.getSession().getServletContext().getRealPath("");
		savePath = savePath.replace("webapp", "resources/static/upload");

		try {
			User user = userRepository.findById(conAssist.getUserno()).get();

			File f = new File(savePath + user.getPicture());
			if (f.exists() && !user.getPicture().equals("profileImg.jpg")) {
				f.delete();
			}

			user.setPicture("/upload/profileImg.jpg");
			return 1;

		} catch (Exception e) {
		}
		return 0;
	}

	// 닉네임 체인지
	@Transactional
	public int nickNameUpdate(String nickName) {
		String namePattern = "^[a-zA-Z0-9가-힣!@#$%^&*()_+-=~.]{2,8}$"; // 한글만 2~8자
		boolean chk = Pattern.matches(namePattern, nickName);

		if (chk == true) {

			try {
				User user = userRepository.findById(conAssist.getUserno()).get();
				user.setNickname(nickName);
				return 1;
			} catch (Exception e) {
			}
		}
		return 0;
	}

	//성별 변경
	@Transactional
	public User userChk(User user) {
		
		if(user.getUserno() == conAssist.getUserno())
		{
			user = conAssist.updateUser();
		}else {
			user = userRepository.findById(user.getUserno()).get();
			System.out.println("친구리스트 lazy init");
			Hibernate.initialize(conAssist.updateUser().getFriendList());
		}
		Hibernate.initialize(user.getBoardList());
		return user;
	}
	
	@Transactional
	public int updateGender(GenderType gender) {
		try {
			User user = userRepository.findById(conAssist.getUserno()).get();
			user.setGender(gender);
			return 1;
		} catch (Exception e) {
		}
		return 0;
	}

	@Transactional
	public int updateAge(int age) {
		try {
			User user = userRepository.findById(conAssist.getUserno()).get();
			user.setAge(age);
			return 1;
		} catch (Exception e) {
		}
		return 0;
	}


}
