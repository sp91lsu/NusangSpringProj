package com.mycom.blog.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.manager.Notice;
import com.mycom.blog.service.manager.NoticeService;

@Controller
@RequestMapping("/manager/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

//	@RequestMapping("/noticesList")
//	public String noticeList(Model model) {
//		List<Notice> list = noticeService.findAll();
//		
//		
//		model.addAttribute("list", list);
//		
//		return "/manager/notice/noticeList";
//	} 
	
	@GetMapping("/noticeList")
	public String index(Model model,
			@PageableDefault(size = 3, sort = "no", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Notice> pageList = noticeService.getPageList(pageable);
		model.addAttribute("list", pageList);
		return "/manager/notice/noticeList";
	}

	@RequestMapping("/noticeWrite")
	public String noticeWrite() {
		return "/manager/notice/noticeWrite";
	}

	@RequestMapping(value = "/noticeWriteOk", method = RequestMethod.POST)
	public String writeOk(Notice dto, Model model) {
		dto = noticeService.save(dto);
		int res = dto != null ? 1 : 0; 
		model.addAttribute("res", res);

		return "/manager/notice/noticeWriteOk";
	}

	@RequestMapping(value = "/noticeView")
	public String view(int no, Model model) {
		Notice view = noticeService.findById(no);
		model.addAttribute("view", view);

		return "/manager/notice/noticeView";
	}

	@RequestMapping(value = "/noticeUpdate")
	public String noticeUpdate(int no, Model model) {
		Notice update = noticeService.findById(no);
		model.addAttribute("update",update);
		return "/manager/notice/noticeUpdate";
	}

	@RequestMapping(value = "/noticeUpdateOk", method = RequestMethod.POST)
	public String noticeUpdateOk(Notice notice, Model model) {

		int res = noticeService.updateOk(notice);
		notice = noticeService.findById(notice.getNo());
		model.addAttribute("notice", notice);
		model.addAttribute("res", res);

		return "/manager/notice/noticeUpdateOk";
	}
	
	@RequestMapping(value="/noticeDeleteOk", method = RequestMethod.POST)
	public String noticeDeleteOk(int no, Model model) {
		int res = noticeService.deleteById(no);
		model.addAttribute("res", res);
		return "/manager/notice/noticeDeleteOk";
	}
}
