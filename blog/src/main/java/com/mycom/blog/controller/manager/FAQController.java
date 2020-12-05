package com.mycom.blog.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.mycom.blog.vo.QNAVO;

@Controller
@RequestMapping("/")
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
	@RequestMapping("/manager/FAQ/faqList")
	public String faqList(Model model,
			@Qualifier("aa") @PageableDefault(size = 10, sort = "no", direction = Sort.Direction.ASC) Pageable pageable,
			@Qualifier("bb") @PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable2) {
		Page<FAQ> faqList = faqService.getPageList(pageable);
		System.out.println("faqList 출력"+ pageable.getPageSize());
		//List qnaList = qnaService.findAll();
		Page<QNAVO> qnaList = qnaService.getPageList(pageable2);
		
		//Page<QNAVO> myQna = QNAService
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("faqList", faqList);
		return "/manager/FAQ/faqList";
	}
	
	@RequestMapping("user/FAQ/faqList")
	public String userFaqList(Model model,
			@Qualifier("aa") @PageableDefault(size = 10, sort = "no", direction = Sort.Direction.ASC) Pageable pageable,
			@Qualifier("cc") @PageableDefault(size = 3, sort = "no", direction = Sort.Direction.DESC) Pageable pageable2) {
		Page<FAQ> faqList = faqService.getPageList(pageable);
		System.out.println("faqList 출력"+ pageable.getPageSize());
		
		Page<QNAVO> qnaList =qnaService.userQnaList(pageable2);
//		
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("faqList", faqList);
		return "/manager/FAQ/faqList";
	} 
	
	@RequestMapping("/manager/FAQ/faqWrite")
	public String faqWrite() {
		return "manager/FAQ/faqWrite";
	}
	
	@RequestMapping(value = "/manager/FAQ/faqWriteOk", method = RequestMethod.POST)
	public void faqWriteOk(FAQ dto, Model model) {
		dto = faqService.save(dto);
		int res = dto != null ? 1 : 0;
		model.addAttribute("res", res);
	}
	@RequestMapping(value = "/manager/FAQ/faqUpdate")
	public void faqUpdate(int no, Model model) {
		FAQ updateDto =faqService.findbyid(no);
		model.addAttribute("updateDto",updateDto );
	}
	@RequestMapping(value = "/manager/FAQ/faqUpdateOk", method = RequestMethod.POST)
	public void faqUpdateOk(FAQ updateDto,Model model) {
		int res = faqService.updateOk(updateDto);
		model.addAttribute("res", res);
	}
	
	
	@RequestMapping(value = "/manager/FAQ/faqDeleteOk", method = RequestMethod.POST)
	public void faqDeleteOk(int no, Model model) {
		System.out.println("no : " + no);
		int res = faqService.deleteById(no);
		model.addAttribute("res", res);
		
	}
	 
	
	
}
