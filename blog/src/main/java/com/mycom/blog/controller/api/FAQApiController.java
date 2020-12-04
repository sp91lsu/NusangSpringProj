package com.mycom.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.service.manager.FAQService;
@RestController
@RequestMapping("manager/FAQ/api/")
public class FAQApiController {
	@Autowired
	private FAQService faqService;
	
	@GetMapping("faqList")
	public Page index(Model model,
			@PageableDefault(size = 10, sort = "no", direction = Sort.Direction.ASC) Pageable pageable) {
		Page faqList = faqService.getPageList(pageable);
		//model.addAttribute("faqList", faqList);

		return faqList;
	}
}
