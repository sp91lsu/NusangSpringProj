package com.mycom.blog.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.mycom.blog.dto.manager.FAQ;
import com.mycom.blog.repository.manager.FAQRepository;

@Service
public class FAQService<E> {
	@Autowired
	private FAQRepository faqRepository;
	
	public List<E> findAll(){
		JpaRepository<E, Integer> e =(JpaRepository<E, Integer>) faqRepository;
		List<E> faqList = e.findAll();
		return faqList;
	}
	
	public E save(E entity) {
		JpaRepository<E, Integer> e =(JpaRepository<E, Integer>) faqRepository;
		 return e.save(entity);
	}
}
