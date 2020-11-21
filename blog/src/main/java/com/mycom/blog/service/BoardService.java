package com.mycom.blog.service;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.Reply;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.model.ReplySaveReq;
import com.mycom.blog.repository.BoardRepository;
import com.mycom.blog.repository.ReplyRepository;
import com.mycom.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
@RequiredArgsConstructor // 선언시 초기화가 꼭 필요한 객체(final)에다가 의존성 주입을 시켜줘!
public class BoardService {

	private final BoardRepository boardRep;
	private final ReplyRepository replyRep;

	@Transactional
	public void writeBoard(Board board, User user) {

		
		System.out.println("BoardService : " + board.getContent());
		board.setUser(user);
		board.setCount(0);
		boardRep.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> getBoardList(Pageable pageable) {
		return boardRep.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board moreInfoDetail(int id) {
		Optional<Board> oBoard = boardRep.findById(id);

		oBoard.orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패");
		});

		return oBoard.get();
	}

	@Transactional
	public void deleteBoad(int id) {

		boardRep.deleteById(id);
	}

	@Transactional
	public void updateBoard(Board board) {

		System.out.println("수정할 보드의 값은 : " + board.getId());
		Board findBoard = boardRep.findById(board.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("업데이트에 실패하였습니다.");
		});

		findBoard.setContent(board.getContent());
		findBoard.setTitle(board.getTitle());
	}

	@Transactional
	public int saveReply(ReplySaveReq dto) {

		System.out.println("replySave1");
//		Board board = boardRep.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("리플을 달던 중 board를 찾을 수 없습니다.");
//		});
//		System.out.println("replySave2");
//		reply.setUser(user);
//		reply.setBoard(board);
//		replyRep.save(reply);

		System.out.println("데이터 확인-----------------------");
		System.out.println(dto.getUserId());
		System.out.println(dto.getBoardId());
		System.out.println(dto.getContent());

		return replyRep.mSave(dto.getUserId(), dto.getBoardId(), dto.getContent());

	}

	@Transactional
	public void deleteReply(int id) {
		System.out.println("삭제한당 리플 : " + id);
		replyRep.deleteById(id);

	}

//	@Transactional(readOnly = true) //정합성 유지
//	public User1 login(User1 user) {
//		return userRep.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
//	

}
