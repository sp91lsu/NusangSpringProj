package com.mycom.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.Location;
import com.mycom.blog.dto.Reply;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.Wish;
import com.mycom.blog.model.ReplySaveReq;
import com.mycom.blog.repository.BoardRepository;
import com.mycom.blog.repository.ReplyRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.blog.repository.WishRepository;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
public class BoardService extends BasicService<BoardRepository, Board> {

	@Autowired
	private UserRepository userRep;
	@Autowired
	private ReplyRepository replyRep;

	@Autowired
	private WishRepository wishRep;

	@Autowired
	public BoardService(BoardRepository boardRep) {
		setRepository(boardRep);
	}

	@Transactional
	public int writeBoard(Board board, User user) {
		try {
			System.out.println("글쓰기 완료");
			board.setUser(user);
			board.setCount(0);
			repository.save(board);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Transactional
	public int deleteBoad(int id) {
		try {
			System.out.println(id + "번 글삭제 완료");
			repository.deleteById(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Transactional
	public int updateBoard(Board board) {
		try {
			System.out.println(board.getId() + "번 글수정 완료");
			Board findBoard = repository.findById(board.getId()).get();
			findBoard.setContent(board.getContent());
			findBoard.setTitle(board.getTitle());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Transactional
	public int saveReply(ReplySaveReq dto) {
		String secret = dto.getSecretmode() == 0 ? "일반댓글" : "비밀댓글";
		System.out.println(secret + " userno:" + dto.getUserId() + " boardno:" + dto.getBoardId());
		System.out.println("내용:" + dto.getContent());

		try {
			System.out.println("댓글쓰기 완료");
			Board board = repository.findById(dto.getBoardId()).get();
			User user = userRep.findById(dto.getUserId()).get();
			Reply reply = Reply.builder().board(board).content(dto.getContent()).user(user).secretmode(dto.getSecretmode()).build();

			replyRep.save(reply);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Transactional
	public int deleteReply(int id) {
		try {
			System.out.println(id + "번 댓글 삭제 완료");
			replyRep.deleteById(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Transactional
	public int updateReply(int id, String comment) {
		try {
			System.out.println(id + "번 댓글 수정 완료");
			Reply reply = replyRep.findById(id).get();
			reply.setContent(comment);
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
	public Page<Board> getNearBoadList(Pageable pageable) {

		User user = ConAssist.getUser();
		if (user != null && user.getLocation() != null) {
			System.out.println("getNearBoardList");
			Location location = user.getLocation();
			System.out.println(location.getLatitude());
			System.out.println(location.getLongtitude());
			System.out.println(location.getView_distance());
			List<Board> boardList = repository.getNearBoardList(location.getLatitude(), location.getLongtitude(),
					location.getView_distance());
			Page<Board> pages = new PageImpl<Board>(boardList, pageable, boardList.size());
			return pages;
		} else {

			List<Board> boardList = repository.findAllByOrderByCreateDateDesc();
			Page<Board> pages = new PageImpl<Board>(boardList, pageable, boardList.size());
			return pages;
		}
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
	public int addWish(int boardno) {

		try {
			Board board = repository.findById(boardno).get();
			Wish wish = Wish.builder().board(board).me(ConAssist.getUser()).build();
			wishRep.save(wish);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Transactional
	public int deleteWish(int boardno) {

		try {
			Board board = repository.findById(boardno).get();
			wishRep.deleteByBoardAndMe(board, ConAssist.getUser());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
