package com.mycom.blog.controller.api;

import java.io.Console;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.Reply;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.model.ReplySaveReq;
import com.mycom.blog.model.Response;
import com.mycom.blog.service.BoardService;
import com.mycom.blog.service.UserService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	@PostMapping("/api/board")
	public Response<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.writeBoard(board, principal.getUser());
		return new Response<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/board/{id}")
	public Response<Integer> delete(@PathVariable int id) {
		System.out.println("게시글 삭제");
		System.out.println("id : " + id);
		boardService.deleteBoad(id);

		return new Response<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/api/board/update")
	public Response<Integer> update(@RequestBody Board board) {

		System.out.println("update : " + board.getId());
		System.out.println("update : " + board.getContent());
		System.out.println("update : " + board.getTitle());
		boardService.updateBoard(board);

		return new Response<Integer>(HttpStatus.OK.value(), 1);
	}

	@PostMapping("/api/board/replysave")
	public Response<Integer> saveReply(@RequestBody ReplySaveReq dto) {
		int result = boardService.saveReply(dto);
		return new Response<Integer>(HttpStatus.OK.value(), result);
	}

	@DeleteMapping("/api/board/reply/{id}")
	public Response<Integer> deleteReply(@PathVariable int id) {
		boardService.deleteReply(id);
		return new Response<Integer>(HttpStatus.OK.value(), 1);
	}
}
