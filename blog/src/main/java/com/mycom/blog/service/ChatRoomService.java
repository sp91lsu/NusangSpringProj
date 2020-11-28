package com.mycom.blog.service;

import java.math.BigDecimal;
import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.dto.ChatMessage;
import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.dto.ChatRoomGuide;
import com.mycom.blog.dto.User;
import com.mycom.blog.model.MessageObject;
import com.mycom.blog.repository.ChatMessageRepository;
import com.mycom.blog.repository.ChatRoomGuideRepository;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.repository.UserRepository;

class Student {

	String name = "aaa";
}

//spring container 

@Service
public class ChatRoomService extends BasicService<ChatRoomRepository, ChatRoom> {

	@Autowired
	private ChatRoomGuideRepository chatRoomGuidRep;
	@Autowired
	private ChatMessageRepository messageRep;

	@Autowired
	private UserRepository userRep;


	@Autowired
	public ChatRoomService(ChatRoomRepository chatRoomRep) {
		setRepository(chatRoomRep);
	}

	@Transactional
	public void openChatRoom(int friendno, User me) {

		try {
			User friend = userRep.findById(friendno).get();

			String topic = conAssist.createTopic(me, friend);

			ChatRoom chatRoom = repository.findByTopic(topic);

			System.out.println(chatRoom);

			if (chatRoom == null) {

				chatRoom = ChatRoom.builder().build();
				chatRoom.setTopic(topic);
				chatRoom = save(chatRoom);
				ChatRoomGuide roomGuide1 = ChatRoomGuide.builder().me(me).chatRoom(chatRoom).build();
				ChatRoomGuide roomGuide2 = ChatRoomGuide.builder().me(friend).chatRoom(chatRoom).build();
				chatRoomGuidRep.save(roomGuide1);
				chatRoomGuidRep.save(roomGuide2);
			}

			System.out.println("채팅방 개설");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public ChatRoom getChatRoom(int friendno, User me) {

		User friend = userRep.findById(friendno).get();

		String topic = conAssist.createTopic(me, friend);

		ChatRoom chatRoom = repository.findByTopic(topic);

		System.out.println(chatRoom);

		return chatRoom;
	}

	@Transactional
	public ChatMessage sendMessage(MessageObject mObj) {

		try {
			ChatRoom chatRoom = repository.findByTopic(mObj.getSubscribe());

			User user = userRep.findById(mObj.getUserno()).get();
			ChatMessage message = ChatMessage.builder().chatRoom(chatRoom).text(mObj.getContent()).user(user).build();
			message = messageRep.save(message);
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@Transactional
	public ChatMessage findMessage(int no) {

		try {
			ChatMessage message = messageRep.findById(no).get();
			
			
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
