package com.mycom.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.dto.Location;
import com.mycom.blog.dto.User;

//DAO 
//자동으로 빈등록 가능 

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	Optional<User> findByUserid(String userid);

	User findByNickname(String nickname);

	User findByEmail(String email);

	@Transactional
	@Modifying
	@Query(value = "UPDATE USER1 SET AVAILABLE_TALK = 3", nativeQuery = true)
	int updateAvailableTalk();

	@Modifying
	@Query(value = "SELECT * FROM USER1 WHERE locationno IN"
			+ "  (SELECT loc.locationno FROM location loc WHERE"
			+ "  calc_distance(?1, ?2,loc.latitude, loc.longtitude ) <= ?3 )", nativeQuery = true)
	List<User> getNearUserList(double latitude, double longtitude, int distance);
}
