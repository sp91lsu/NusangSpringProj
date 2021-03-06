package com.mycom.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.User;

//DAO 
//자동으로 빈등록 가능 

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	Board findByTitle(String title);
	
	Page<Board> findByUser(User user,Pageable pageable);
	
	
	@Modifying
	@Query(value =
	"SELECT * FROM " + 
	"BOARD WHERE " + 
	"BOARD.userno IN " + 
	"(SELECT userno " + 
	" FROM USER1 " + 
	" WHERE userno IN " + 
	" (SELECT loc.userno FROM location loc WHERE calc_distance(?1, ?2,loc.latitude, loc.longtitude ) <= ?3 )) " + 
	" ORDER BY CREATE_DATE DESC",nativeQuery = true)
	List<Board> getNearBoardList(double latitude, double longtitude , int distance);
	
	List<Board> findAllByOrderByCreateDateDesc(); 
}
