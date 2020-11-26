package com.mycom.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycom.blog.dto.Board;
import com.mycom.blog.service.BoardService;

@Controller
@RequestMapping(value = "/post")
public class PostController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/post_write") // 글쓰기
	public String write() {
		System.out.println("글쓰기 이동");
		return "/post/post_write";
	}

	@GetMapping(value = "/post_read/{id}") // 글 읽기
	public String read(Board board, Model model) {
		System.out.println(board.getId() + "번 글읽기 이동");
		Board post_no = boardService.findById(board.getId());
		model.addAttribute("board", post_no);
		return "post/post_read";
	}

	@GetMapping(value = "/post_update/{id}") // 글 수정
	public String update(Board board, Model model) {
		System.out.println(board.getId() + "번 수정페이지 이동");
		Board post_no = boardService.findById(board.getId());
		model.addAttribute("board", post_no);
		return "post/post_update";
	}
}
