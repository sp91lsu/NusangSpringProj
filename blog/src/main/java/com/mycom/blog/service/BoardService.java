package com.mycom.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.User;
import com.mycom.blog.model.ReplySaveReq;
import com.mycom.blog.repository.BoardRepository;
import com.mycom.blog.repository.ReplyRepository;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
public class BoardService extends BasicService<BoardRepository, Board>{

	@Autowired
	private  ReplyRepository replyRep;

	
	@Autowired
	public BoardService(BoardRepository boardRep) {
		setRepository(boardRep);
	}
	
	@Transactional
	public int writeBoard(Board board, User user) {
		try {
			System.out.println("BoardService : " + board.getContent());
			board.setUser(user);
			board.setCount(0);
			repository.save(board);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Transactional(readOnly = true)
	public Page<Board> getBoardList(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board moreInfoDetail(int id) {
		Optional<Board> oBoard = repository.findById(id);

		oBoard.orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패");
		});

		return oBoard.get();
	}

	@Transactional
	public void deleteBoad(int id) {

		repository.deleteById(id);
	}

	@Transactional
	public void updateBoard(Board board) {

		System.out.println("수정할 보드의 값은 : " + board.getId());
		Board findBoard = repository.findById(board.getId()).orElseThrow(() -> {
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
