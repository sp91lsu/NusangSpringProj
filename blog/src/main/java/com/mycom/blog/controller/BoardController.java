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
	@GetMapping("/home")
	public String index(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		
		System.out.println("page :" + pageable.getPageNumber());
		Page<Board> pageList = boardService.getNearBoadList(pageable);
//		System.out.println("getTotalPages" + pageList.getTotalPages());
//		System.out.println("getNumber" + pageList.getNumber()); //현재 페이지 
//		System.out.println("getSize" + pageList.getSize());// 페이 안의 최대 사이즈
//		System.out.println("getNumberOfElements" + pageList.getNumberOfElements()); //페이지 안의 실제 갯수
//		System.out.println("isLast" + pageList.isLast()); //페이지 안의 실제 갯수
		model.addAttribute("boards", pageList);
		
		

		return "index";
	}

	@GetMapping("/board/writeForm")
	public String writeForm() {
		return "board/writeForm";
	}

	@GetMapping("/board/{id}") // 모델을 통해서 다음페이지에 쓸 객체를 담는다.
	public String findById(@PathVariable int id, Model model) {

		model.addAttribute("board", boardService.moreInfoDetail(id));
		return "board/detail";
	}

	@GetMapping("/board/{id}/updateForm")
	public String updateBoard(@PathVariable int id, Model model) {

		model.addAttribute("board", boardService.moreInfoDetail(id));

		return "board/updateForm";
	}

}
