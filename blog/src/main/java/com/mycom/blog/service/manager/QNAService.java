package com.mycom.blog.service.manager;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.mycom.blog.dto.manager.FAQ;
import com.mycom.blog.repository.manager.FAQRepository;
import com.mycom.blog.repository.manager.QNARepository;

@Service
public class QNAService {
	@Autowired
	private QNARepository qnaRepository;
	
	@Transactional
	public List findAll(){
		List faqList = qnaRepository.findAll();
		return faqList;
	}
	@Transactional
	public FAQ save(FAQ dto) {
		FAQ faq = qnaRepository.save(dto);
		 return faq;
	}
	@Transactional
	public FAQ findbyid(int id) {
		
		FAQ faq =qnaRepository.findById(id).get();
		return faq;
	}
	
	@Transactional
	public int updateOk(FAQ updateFAQ) {
		try {
			FAQ faq = qnaRepository.findById(updateFAQ.getNo()).get();
			faq.setTitle(updateFAQ.getTitle());
			faq.setContents(updateFAQ.getContents());
			return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Transactional
	public int deleteById(int no) {
		try {
			qnaRepository.deleteById(no);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
}
