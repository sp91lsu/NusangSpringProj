package com.mycom.blog.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;
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

	//사진 업데이트
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
			if (f.exists()) {
				f.delete();
			}

			WatchService watchService = FileSystems.getDefault().newWatchService();
			// 경로 생성
			Path path = Paths
					.get("C:/Users/Sunghoon/Desktop/nusangSpringProject/blog/src/main/resources/static/upload");
			// 해당 디렉토리 경로에 와치서비스와 이벤트 등록
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW);

			File newFile = new File(savePath + fileName);
			file.transferTo(newFile);

			user.setPicture(fileName);
			boolean isQuit = false;
			while (!isQuit) {
				try {
					watchKey = watchService.take();// 이벤트가 오길 대기(Blocking)
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				List<WatchEvent<?>> events = watchKey.pollEvents();// 이벤트들을 가져옴
				for (WatchEvent<?> event : events) {
					// 이벤트 종류
					Kind<?> kind = event.kind();
					// 경로
					Path paths = (Path) event.context();
					System.out.println(paths.toAbsolutePath());// C:\...\...\test.txt
					if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
						System.out.println("created something in directory");
						isQuit = true;
					}
					if (!watchKey.reset()) {
						try {
							watchService.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}

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

	
	//닉네임 체인지
	@Transactional
	public int nickNameUpdate(String nickName) {
		
		try {
			User user = userRepository.findById(conAssist.getUserno()).get();
			user.setNickname(nickName);
			
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	// 프로젝트 경로

	private WatchKey watchKey;

}
