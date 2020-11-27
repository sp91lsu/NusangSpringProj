package com.mycom.blog.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.mycom.blog.dto.manager.FAQ;
import com.mycom.blog.repository.manager.FAQRepository;

@Service
public class FAQService {
	@Autowired
	private FAQRepository faqRepository;
	
	public List findAll(){
		List faqList = faqRepository.findAll();
		return faqList;
	}
	
	public FAQ save(FAQ dto) {
		FAQ faq = faqRepository.save(dto);
		 return faq;
	}
	public FAQ findbyid(int id) {
		
		FAQ faq =faqRepository.findById(id).get();
		return faq;
	}
	
	public int updateOk(FAQ updateDto) {
		try {
			FAQ dto = faqRepository.findById(updateDto.getNo()).get();
			dto.setTitle(updateDto.getTitle());
			dto.setContents(updateDto.getContents());
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
