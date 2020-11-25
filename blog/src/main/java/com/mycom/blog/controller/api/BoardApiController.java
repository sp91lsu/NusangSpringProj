package com.mycom.blog.controller.api;

import java.io.Console;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
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

	@GetMapping("/api/paging")
	public Page<Board> index(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

		System.out.println("page :" + pageable.getPageNumber());
		Page<Board> pageList = boardService.getBoardList(pageable);
//		System.out.println("getTotalPages" + pageList.getTotalPages());
//		System.out.println("getNumber" + pageList.getNumber()); // 현재 페이지
//		System.out.println("getSize" + pageList.getSize());// 페이 안의 최대 사이즈
//		System.out.println("getNumberOfElements" + pageList.getNumberOfElements()); // 페이지 안의 실제 갯수
//		System.out.println("isLast" + pageList.isLast()); // 페이지 안의 실제 갯수
//		
		model.addAttribute("boards", pageList);

		return pageList;
	}

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
