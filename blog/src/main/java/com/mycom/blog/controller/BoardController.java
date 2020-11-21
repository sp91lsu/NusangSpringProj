package com.mycom.blog.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.dto.Board;
import com.mycom.blog.service.BoardService;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	// @AuthenticationPrincipal PrinciapalDetail principal
	@GetMapping("/home")
	public String index(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Board> pageList = boardService.getBoardList(pageable);
		model.addAttribute("boards", pageList);

		return "index";
	}

	@GetMapping("/board/writeForm")
	public String writeForm() {
		return "board/writeForm";
	}

	@GetMapping("/board/{id}") //모델을 통해서 다음페이지에 쓸 객체를 담는다. 
	public String findById(@PathVariable int id,Model model) {
		
		model.addAttribute("board",boardService.moreInfoDetail(id));
		return "board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateBoard(@PathVariable int id,Model model) {
		
		model.addAttribute("board",boardService.moreInfoDetail(id));
		
		
		return "board/updateForm";
	}

}
