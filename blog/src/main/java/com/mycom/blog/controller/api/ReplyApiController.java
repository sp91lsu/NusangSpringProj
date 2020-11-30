package com.mycom.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.Reply;
import com.mycom.blog.dto.User;
import com.mycom.blog.model.ReplySaveReq;
import com.mycom.blog.service.BoardService;

@RestController
public class ReplyApiController {

	@Autowired
	private BoardService boardService;

	@PostMapping("/api/reply/write")
	public int write(ReplySaveReq replydto) {
		int result = boardService.saveReply(replydto);
		return result;
	}

	@PostMapping("/api/reply/delete")
	public int delete(int reply_id) {
		int result = boardService.deleteReply(reply_id);
		return result;
	}

	@PostMapping()
	public int update() {
		return 1;
	}
}
