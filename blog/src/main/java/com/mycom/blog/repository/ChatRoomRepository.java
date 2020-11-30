package com.mycom.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.dto.User;

//DAO 
//자동으로 빈등록 가능 

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer>{
	
	
	ChatRoom findByTopic(String topic);
	
	@Modifying
	@Query(value = "SELECT * FROM chat_Room " + 
			"WHERE roomno IN " + 
			"(SELECT chat_room_roomno FROM chat_room_guide WHERE " + 
			"me_userno = ?1) ORDER BY update_date DESC", nativeQuery = true)
	List<ChatRoom> getUserChatRoomList(int userno);
	
	
}
