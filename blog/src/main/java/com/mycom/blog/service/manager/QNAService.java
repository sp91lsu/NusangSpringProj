package com.mycom.blog.service.manager;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.manager.FAQ;
import com.mycom.blog.dto.manager.QNA;
import com.mycom.blog.handler.QNATransfer;
import com.mycom.blog.repository.manager.FAQRepository;
import com.mycom.blog.repository.manager.QNARepository;
import com.mycom.blog.service.UserService;
import com.mycom.blog.vo.QNAVO;
import com.mycom.blog.vo.UserVO;

@Service
public class QNAService {
	@Autowired
	private QNARepository qnaRepository;

	
	@Transactional
	public List findAll() {
		List faqList = qnaRepository.findAll();
		return faqList;
	}

	@Transactional
	public QNA save(QNA dto) {
		QNA qna = qnaRepository.save(dto);
		return qna;
	}

	@Transactional
	public QNA findbyid(int id) {

		QNA qna = qnaRepository.findById(id).get();
		return qna;
	}

	@Transactional
	public int updateOk(QNA updateQNA) {
		try {
			System.out.println("updateQNA.getNo() 모시여");
			QNA qna = qnaRepository.findById(updateQNA.getNo()).get();
			qna.setAnswer(updateQNA.getAnswer());
			System.out.println("qna.getAnswer : " + qna.getAnswer());
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Transactional
	public int deleteById(int no) {
		try {
			qnaRepository.deleteById(no);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Transactional()
	public Page<QNAVO> getPageList(Pageable pageable) {

		Page<QNA> qnaList = qnaRepository.findAll(pageable);

		return QNATransfer.listToPage(pageable, qnaList);
	}

	@Transactional
	public Page<QNAVO> userQnaList(Pageable pageable) {
		
		Page<QNA> qnaList = qnaRepository.findByMe(ConAssist.getUser(),pageable);
		return QNATransfer.listToPage(pageable, qnaList);
	}
}
