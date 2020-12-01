package com.mycom.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.Board;
import com.mycom.blog.model.Response;
import com.mycom.blog.service.BoardService;

@RestController
@RequestMapping(value = "/api/post")
public class PostApiController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private ConAssist conAssist;

	@PostMapping("")
	public int write(Board board) {
		int result = boardService.writeBoard(board, conAssist.getUser());
		return result;
	}

	@DeleteMapping("/{id}")
	public int delete(Board board) {
		int result = boardService.deleteBoad(board.getId());
		return result;
	}

	@PutMapping("/update")
	public int update(Board board) {
		int result = boardService.updateBoard(board);
		return result;
	}

	@PostMapping("/add_wish")
	public int set_wishList(int boardno) {
		int result = boardService.addWish(boardno);
		return result;
	}
	
	@PostMapping("/delete_wish")
	public int delete_wishList(int boardno) {
		int result = boardService.deleteWish(boardno);
		return result;
	}

}
