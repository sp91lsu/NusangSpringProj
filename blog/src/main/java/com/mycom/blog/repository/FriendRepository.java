package com.mycom.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.dto.Friend;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.FriendType;

//DAO 
//자동으로 빈등록 가능 

public interface FriendRepository extends JpaRepository<Friend, Integer> {

	Friend findByMeAndUser(User me, User user);
	
	Friend findByMeAndUserAndFriendType(User me, User user,FriendType type);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FRIEND " + 
			"WHERE userno = ?1 " + 
			"AND user_userno = ?2 " + 
			"OR " + 
			"userno = ?2 " + 
			"AND user_userno = ?1", nativeQuery = true)
	int breakFriend(int me, int userno);
}
