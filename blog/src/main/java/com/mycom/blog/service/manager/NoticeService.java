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
import com.mycom.blog.service.BasicService;
@Service
public class NoticeService extends BasicService<NoticeRepository, Notice>{
	
	@Autowired
	public NoticeService(NoticeRepository rep) {
		setRepository(rep);
	}
	
	public List<Notice> findAll() {
		List<Notice> list = repository.findAll();
		return list;
	}
	
	
	@Transactional
	public int updateOk(Notice updateDto) {
		try {
			Notice notice = repository.findById(updateDto.getNo()).get();
			notice.setTitle(updateDto.getTitle());
			notice.setContents(updateDto.getContents());
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
}
