package com.mycom.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mycom.blog.dto.ChatMessage;
import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.dto.Item;
import com.mycom.blog.dto.Shop;
import com.mycom.blog.dto.User;

//DAO 
//자동으로 빈등록 가능 

public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	
}
