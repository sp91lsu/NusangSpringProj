package com.mycom.blog.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.dto.manager.Notice;
import com.mycom.blog.repository.manager.NoticeRepository;
@Service
public class NoticeService {
	@Autowired
	NoticeRepository repository;
	
	public List<Notice> findAll() {
		List<Notice> list = repository.findAll();
		return list;
	}
	@Transactional
	public int save(Notice dto) {
		try {
			
			repository.save(dto);
			
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}
}
