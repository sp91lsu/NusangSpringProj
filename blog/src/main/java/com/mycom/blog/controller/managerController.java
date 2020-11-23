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
	public String noticeList(Model model){
		List<Notice> list = noticeService.findAll();
		model.addAttribute("list",list);
		return "/manager/noticeList";
	}
	@RequestMapping("/noticeWrite")
	public String noticeWrite() {
		return "/manager/noticeWrite";
	}
	@RequestMapping(value="/writeOk", method = RequestMethod.POST)
	public String writeOk(Notice dto, Model model) {
		int res = noticeService.save(dto);
		model.addAttribute("res", res);
		
		return "/manager/writeOk";
	}
	
//	@RequestMapping("/view.do")
//	public String view(int uid, Model model) {
//		model.addAttribute("uid", uid);
//		new BViewCommand().execute(model);
//		return "board/view";
//	}
//	
//	@RequestMapping("/update.do")
//	public String update(int uid, Model model) {
//		model.addAttribute("uid", uid);
//		new BSelectCommand().execute(model);		
//		return "board/update";
//	}
//	
//	@RequestMapping(value="/updateOk.do", method= RequestMethod.POST)
//	public String updateOk(Notice dto, Model model) {
//		model.addAttribute("dto", dto);
//		
//		new BUpdateCommand().execute(model);
//		
//		return "board/updateOk";
//	}
//	
//	@RequestMapping(value="/deleteOk.do")
//	public String deleteOk(int uid, Model model) {
//		model.addAttribute("uid", uid);
//		new BDeleteCommand().execute(model);
//		return "board/deleteOk";
//	}
}
