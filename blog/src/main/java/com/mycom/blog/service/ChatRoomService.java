package com.mycom.blog.service;

import java.math.BigDecimal;
import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.dto.User;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.jooq.tables.JChatRoom;
import com.mycom.jooq.tables.records.JChatRoomRecord;




class Student{
	
	String name = "aaa";
}

//spring container 





@Service 
public class ChatRoomService extends BasicService<ChatRoom> {

	private ChatRoomRepository chatRoomRep;
	
	@Autowired
	DSLContext dsl;

	@Autowired
	private UserRepository userRep;

	private JChatRoom jChatRoom = JChatRoom.CHAT_ROOM;

	@Autowired
	public ChatRoomService(ChatRoomRepository chatRoomRep) {
		this.chatRoomRep = chatRoomRep;
		setRepository(chatRoomRep);
	}

	@Transactional
	public ChatRoom openChatRoom(int friendno, User me) {

		JChatRoomRecord recode = dsl.select().from(jChatRoom)
				.where(jChatRoom.CHAT_USER_USERNO.eq(friendno), jChatRoom.ME_USERNO.eq(me.getUserno()))
				.fetchOneInto(jChatRoom);
		
		System.out.println(recode);

		User friend = userRep.findById(friendno).get();

		ChatRoom chatRoom = null;
		if (recode == null) {

			chatRoom = ChatRoom.builder().chatUser(friend).me(me).build();
			chatRoom.setTopic(me, friend);
			chatRoom = save(chatRoom);
		} else {
			System.out.println("채팅방 개설" + recode.getRoomno());
			chatRoom = findById(Integer.parseInt(recode.getRoomno().toString()));
		}

		System.out.println("채팅방 개설");

		return chatRoom;
	}

}
