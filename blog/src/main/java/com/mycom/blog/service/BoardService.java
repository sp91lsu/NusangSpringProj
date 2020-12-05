package com.mycom.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
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
import com.mycom.blog.vo.BoardVO;
import com.mycom.blog.vo.UserVO;

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
	private BoardRepository boardRep;

	@Autowired
	public BoardService(BoardRepository boardRep) {
		setRepository(boardRep);
	}

	@Transactional
	public int writeBoard(Board board, User user) {
		try {
			board.setUser(user);
			board.setCount(0);
			repository.save(board);
			System.out.println("글쓰기 완료");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Transactional
	public int deleteBoad(int id) {
		try {
			repository.deleteById(id);
			System.out.println(id + "번 글삭제 완료");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Transactional
	public int updateBoard(Board board) {
		try {
			Board findBoard = repository.findById(board.getId()).get();
			findBoard.setContent(board.getContent());
			findBoard.setTitle(board.getTitle());
			System.out.println(board.getId() + "번 글수정 완료");
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
			Board board = repository.findById(dto.getBoardId()).get();
			User user = userRep.findById(dto.getUserId()).get();
			Reply reply = Reply.builder().board(board).content(dto.getContent()).user(user)
					.secretmode(dto.getSecretmode()).build();

			replyRep.save(reply);
			System.out.println("댓글쓰기 완료");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Transactional
	public int deleteReply(int id) {
		try {
			replyRep.deleteById(id);
			System.out.println(id + "번 댓글 삭제 완료");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Transactional
	public int updateReply(int id, String comment) {
		try {
			Reply reply = replyRep.findById(id).get();
			reply.setContent(comment);
			System.out.println(id + "번 댓글 수정 완료");
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
	public Page<BoardVO> getPostList(Pageable pageable) {
		Page<Board> boards = boardRep.findAll(pageable);
		List<BoardVO> PostList = new ArrayList<BoardVO>();

		for (Board board : boards) {
			BoardVO vo = new BoardVO();
			UserVO userVo = new UserVO();
			BeanUtils.copyProperties(board, vo);
			BeanUtils.copyProperties(board.getUser(), userVo);
			vo.setUser(userVo);
			PostList.add(vo);
		}

		Page<BoardVO> boardVoPage = new PageImpl<BoardVO>(PostList, pageable, boards.getTotalElements());

		return boardVoPage;
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

	@Transactional
	public Board readPost(int boardno, HttpServletRequest request, HttpServletResponse response) {
		Board post = repository.findById(boardno).get();
		int viewCnt = post.getViewcnt();

		if (post != null) {// 글이 존재하면
			Cookie[] cookies = request.getCookies();
			Cookie viewCookie = null;

			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("VIEWCOOKIE")) { // Cookie의 name이 VIEWCOOKIE와 일치하는 쿠키를
																		// viewCookie에넣어준다.
						viewCookie = cookies[i];
					}
				}
			}

			// 만일 viewCookie없다면
			if (viewCookie == null) {
				Cookie newCookie = new Cookie("VIEWCOOKIE", "|" + post.getId() + "|");
				response.addCookie(newCookie);
				post.setViewcnt(viewCnt + 1);
			} else {
				String value = viewCookie.getValue();

				if (value.indexOf("|" + post.getId() + "|") < 0) { // 입력한 번화와 일치하는 번호가 없으면 추가한다.
					value = value + "|" + post.getId() + "|";
					viewCookie.setValue(value);
					viewCookie.setMaxAge(60 * 60 * 12);
					response.addCookie(viewCookie);
					post.setViewcnt(viewCnt + 1);
				}
			}
		}
		return post;
	}
}
