package com.mycom.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.Board;
import com.mycom.blog.model.Response;
import com.mycom.blog.service.BoardService;

@RestController
public class PostApiController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private ConAssist conAssist;

	@PostMapping("/api/post")
	public int write(Board board) {
		System.out.println("경로:"+"api/post");
		int result = boardService.writeBoard(board, conAssist.getUser());
		return result;
	}
	
	@DeleteMapping("/api/post/{id}")
	public Response<Integer> delete(@PathVariable int id) {
		System.out.println("게시글 삭제");
		System.out.println("id : " + id);
		boardService.deleteBoad(id);

		return new Response<Integer>(HttpStatus.OK.value(), 1);
	}
}
