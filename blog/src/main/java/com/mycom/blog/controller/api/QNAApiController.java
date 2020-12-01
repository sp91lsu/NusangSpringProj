package com.mycom.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.dto.manager.QNA;
import com.mycom.blog.service.manager.QNAService;
@RestController
@RequestMapping("manager/QNA")
public class QNAApiController {
	@Autowired
	private QNAService qnaService;
	
	@GetMapping("api/qnaList")
	public Page index(Model model,
			@PageableDefault(size = 3, sort = "no", direction = Sort.Direction.ASC) Pageable pageable) {
		Page qnaList = qnaService.getPageList(pageable);
		//model.addAttribute("faqList", faqList);

		return qnaList;
	}
	@PostMapping(value = "api/qnaUpdateOk")
	public int qnaUpdateOk(QNA updateQNA,Model model) {
		System.out.println("답변달기 탔니????");
		int res = qnaService.updateOk(updateQNA);
		return res;
	}
}
