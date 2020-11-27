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
public class FAQController {
	@Autowired
	FAQService faqService;
	
	@RequestMapping("/faqList")
	public String faqList(Model model) {
		List faqList = faqService.findAll();
		model.addAttribute("list", faqList);
		return "/manager/FAQ/faqList";
	}
	@RequestMapping("faqWrite")
	public String faqWrite() {
		return "manager/FAQ/faqWrite";
	}
	
	@RequestMapping(value = "faqWriteOk", method = RequestMethod.POST)
	public void faqWriteOk(FAQ dto, Model model) {
		dto = faqService.save(dto);
		int res = dto != null ? 1 : 0;
		model.addAttribute("res", res);
	}
	@RequestMapping(value = "faqUpdate")
	public void faqUpdate(int no, Model model) {
		FAQ updateDto =faqService.findbyid(no);
		model.addAttribute("updateDto",updateDto );
	}
	@RequestMapping(value = "faqUpdateOk", method = RequestMethod.POST)
	public void faqUpdateOk(int no, FAQ dto,Model model) {
		int res = faqService.updateOk(dto);
		dto = faqService.findbyid(no);
		model.addAttribute("dto", dto);
		model.addAttribute("res", res);
	}
	
	
}
