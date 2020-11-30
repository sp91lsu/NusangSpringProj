package com.mycom.blog.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycom.blog.dto.manager.FAQ;
import com.mycom.blog.dto.manager.QNA;
import com.mycom.blog.service.manager.FAQService;
import com.mycom.blog.service.manager.QNAService;

@Controller
@RequestMapping("/manager/FAQ")
public class FAQController {
	@Autowired
	FAQService faqService;
	@Autowired
	QNAService qnaService;
//	@RequestMapping("/faqList")
//	public String faqList(Model model) {
//		List faqList = faqService.findAll();
//		model.addAttribute("list", faqList);
//		return "/manager/FAQ/faqList";
//	}
	@RequestMapping("/faqList")
	public String faqList(Model model,
			@PageableDefault(size = 3, sort = "no", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<FAQ> faqList = faqService.getPageList(pageable);
		System.out.println("faqList 출력");
		//List qnaList = qnaService.findAll();
		Page<QNA> qnaList = qnaService.getPageList(pageable);
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("faqList", faqList);
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
	public void faqUpdateOk(FAQ updateDto,Model model) {
		int res = faqService.updateOk(updateDto);
		model.addAttribute("res", res);
	}
	
	
	@RequestMapping(value = "/faqDeleteOk", method = RequestMethod.POST)
	public void faqDeleteOk(int no, Model model) {
		int res = faqService.deleteById(no);
		model.addAttribute("res", res);
		
	}
	 
	
}
