package com.mycom.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mycom.blog.dto.Board;
import com.mycom.blog.service.BoardService;
import com.mycom.blog.vo.BoardVO;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping({ "/", "/home"})
	public String index(Model model,
			@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

		Page<Board> postList = boardService.getNearBoadList(pageable);
		model.addAttribute("myPostList", postList);

		return "/index";
	}
}
