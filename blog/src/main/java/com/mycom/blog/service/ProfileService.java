package com.mycom.blog.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.User;
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
	
	@Transactional
	public int updatePicture(MultipartFile file, HttpServletRequest request) {
		
		String savePath = request.getSession().getServletContext().getRealPath("");
		savePath = savePath.replace("webapp", "resources/static/upload");
		System.out.println("업로드 경로:  " + savePath);
		
		Date current = new Date();
		String fileName = current.getTime() + "_" + file.getOriginalFilename();
 
		try {
			
			file.transferTo(new File(savePath + fileName));
			conAssist.getUserno();
			User user = userRepository.findById(conAssist.getUserno()).get();
			
			//이전 사진파일 지우기
			File f = new File(savePath + user.getPicture());
			if(f.exists())f.delete();
			
			user.setPicture(fileName);
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

}
