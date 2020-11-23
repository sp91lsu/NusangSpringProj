package com.mycom.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycom.blog.dto.User;
import com.mycom.blog.dto.manager.Notice;
import com.mycom.blog.repository.manager.NoticeRepository;
import com.mycom.blog.service.manager.NoticeService;

@Controller
@RequestMapping("/manager")
public class managerController {
	@Autowired
	private NoticeService noticeService;

	@RequestMapping("/noticeList")
	public String noticeList(Model model) {
		List<Notice> list = noticeService.findAll();
		model.addAttribute("list", list);
		return "/manager/noticeList";
	}

	@RequestMapping("/noticeWrite")
	public String noticeWrite() {
		return "/manager/noticeWrite";
	}

	@RequestMapping(value = "/noticeWriteOk", method = RequestMethod.POST)
	public String writeOk(Notice dto, Model model) {
		dto = noticeService.save(dto);
		int res = dto != null ? 1 : 0; 
		model.addAttribute("res", res);

		return "/manager/noticeWriteOk";
	}

	@RequestMapping("/noticeView")
	public String view(int no, Model model) {
		Notice view = noticeService.findById(no);
		model.addAttribute("view", view);

		return "/manager/noticeView";
	}

	@RequestMapping("/noticeUpdate")
	public String noticeUpdate(int no, Model model) {
		Notice update = noticeService.findById(no);
		model.addAttribute("update",update);
		return "/manager/noticeUpdate";
	}

	@RequestMapping(value = "/noticeUpdateOk", method = RequestMethod.POST)
	public String noticeUpdateOk(Notice notice, Model model) {

		int res = noticeService.updateOk(notice);
		notice = noticeService.findById(notice.getNo());
		model.addAttribute("notice", notice);
		model.addAttribute("res", res);

		return "/manager/noticeUpdateOk";
	}
//	
//	@RequestMapping(value="/deleteOk.do")
//	public String deleteOk(int uid, Model model) {
//		model.addAttribute("uid", uid);
//		new BDeleteCommand().execute(model);
//		return "board/deleteOk";
//	}
}
