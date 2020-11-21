package com.mycom.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mycom.blog.dto.Reply;


public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	 
	@Modifying
	@Query(value = "INSERT INTO REPLY(id,user_id,board_id,content,create_date) VALUES (REPLY_SEQ.NEXTVAL,?1,?2,?3,sysdate)",nativeQuery = true) 
	int mSave(int userId,int boardId , String content);
	
	
	
	
}