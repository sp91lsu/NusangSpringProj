package com.mycom.blog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.dto.manager.Notice;
import com.mycom.blog.service.manager.NoticeService;

@RestController
@RequestMapping("/manager/notice/")
public class NoticeApiController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("api/noticeList")
	public Page<Notice> index(Model model,
			@PageableDefault(size = 3, sort = "no", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Notice> pageList = noticeService.getPageList(pageable);
		model.addAttribute("noticeList", pageList);

		return pageList;
	}
}
