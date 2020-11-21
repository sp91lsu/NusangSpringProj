package com.mycom.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mycom.blog.dto.Friend;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.simple.SimpleUser;

//DAO 
//자동으로 빈등록 가능 

public interface FriendRepository extends JpaRepository<Friend, Integer>{
	
	
}
