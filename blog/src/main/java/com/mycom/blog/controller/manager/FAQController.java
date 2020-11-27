package com.mycom.blog.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycom.blog.dto.manager.FAQ;
import com.mycom.blog.service.manager.FAQService;

@Controller
@RequestMapping("/manager/FAQ")
public class FAQController<E> {
	@Autowired
	FAQService faqService;
	
	@RequestMapping("/faqList")
	public String faqList(Model model) {
		List<E> faqList = faqService.findAll();
		model.addAttribute("list", faqList);
		return "/manager/FAQ/faqList";
	}
	@RequestMapping("faqWrite")
	public String faqWrite() {
		return "manager/FAQ/faqWrite";
	}
	
	@RequestMapping(value = "faqWriteOk", method = RequestMethod.POST)
	public String faqWriteOk(FAQ dto, Model model) {
		dto = (FAQ)faqService.save(dto);
		int res = dto != null ? 1 : 0;
		model.addAttribute("res", res);
		return "/manager/FAQ/faqWriteOk";
	}
}
