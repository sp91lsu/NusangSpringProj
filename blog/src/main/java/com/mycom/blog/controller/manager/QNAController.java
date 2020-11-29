package com.mycom.blog.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
}
