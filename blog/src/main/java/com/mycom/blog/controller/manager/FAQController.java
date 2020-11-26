package com.mycom.blog.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycom.blog.dto.manager.FAQ;
import com.mycom.blog.service.manager.FAQService;

@Controller
@RequestMapping("/manager/FAQ")
public class FAQController {
	@Autowired
	FAQService faqService;
	
	@RequestMapping("/faqList")
	public String faqList(Model model) {
		//List<FAQ> list = faqService.
		
		return "/manager/FAQ/faqList";
	}
}
