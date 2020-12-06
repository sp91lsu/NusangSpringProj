package com.mycom.blog.repository;

import java.util.List;

import java.util.Optional;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.dto.Location;
import com.mycom.blog.dto.User;

//DAO 
//자동으로 빈등록 가능 

public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {

	Optional<User> findByUsername(String username);

	Optional<User> findByUserid(String userid);

	User findByNickname(String nickname);

	User findByEmail(String email);
	
	//검색조건 조합해서 검색
	List<User> findAll(Specification<User> spec,Sort sort);
	
	@Query(value = "SELECT * FROM USER1 ORDER BY USERNO", nativeQuery = true)
	List<User> findAllSortByUserno();
	

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
