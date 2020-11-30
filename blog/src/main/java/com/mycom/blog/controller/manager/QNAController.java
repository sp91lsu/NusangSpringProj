package com.mycom.blog.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycom.blog.dto.manager.QNA;
import com.mycom.blog.service.manager.QNAService;

@Controller
@RequestMapping("/manager/QNA")
public class QNAController {
	@Autowired
	QNAService qnaService;

	@RequestMapping("/qnaList")
	public String qnaList(Model model) {
		List qnaList = qnaService.findAll();
		model.addAttribute("qnaList", qnaList);
		return "/manager/FAQ/faqList";
	}
	@RequestMapping(value = "qnaWriteOk",method = RequestMethod.POST)
	public String qnaWriteOk(QNA qna,Model model) {
		System.out.println("글쓰기 탔니???");
		qna = qnaService.save(qna);
		int res = qna != null ? 1 : 0;
		model.addAttribute("res", res);
		return "/manager/QNA/qnaWriteOk";
	}
	
	
}
