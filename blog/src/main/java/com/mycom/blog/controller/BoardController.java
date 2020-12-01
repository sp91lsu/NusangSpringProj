package com.mycom.blog.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mycom.blog.dto.Board;
import com.mycom.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// @AuthenticationPrincipal PrinciapalDetail principal
	@GetMapping({"/","/home"})
	public String index(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		
		System.out.println("page :" + pageable.getPageNumber());
		Page<Board> pageList = boardService.getNearBoadList(pageable);
		model.addAttribute("boards", pageList);

		return "/index";
	}
}
