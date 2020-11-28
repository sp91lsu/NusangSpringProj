package com.mycom.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.Reply;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.Wish;


public interface WishRepository extends JpaRepository<Wish, Integer>{
	 
	
	
	int deleteByBoardAndMe(Board board, User user);
	
}