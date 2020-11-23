package com.mycom.blog.repository.manager;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mycom.blog.dto.User;
import com.mycom.blog.dto.manager.Notice;

//DAO 
//자동으로 빈등록 가능 

public interface NoticeRepository extends JpaRepository<Notice, Integer>{
	
	Notice findbyContents(String contents);
//	Optional<Notice> findByUsername(String username);
//	Optional<Notice> findByUserid(String userid);
//	User findByNickname(String nickname);
//	User findByEmail(String email);
	//jpa 네이밍 전략 findBy 바로 다음에 올 컬럼명을 밑에와 같이 입력하면 쿼리가 작동한다. 그다음 컬럼은 and를 붙여 사용
//		User1 findByUsernameAndPassword(String username,String password);
		
//		@Query(value = "SELECT * FROM user1 WHERE username = ?1 AND password = ?2",nativeQuery = true)
//		User1 login(String username, String password);
}
