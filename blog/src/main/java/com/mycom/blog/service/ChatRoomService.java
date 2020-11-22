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
import com.mycom.jooq.tables.JChatRoom;
import com.mycom.jooq.tables.records.JChatRoomRecord;

class Student {

	String name = "aaa";
}

//spring container 

@Service
public class ChatRoomService extends BasicService<ChatRoom> {

	private ChatRoomRepository chatRoomRep;

	@Autowired
	private ChatRoomGuideRepository chatRoomGuidRep;
	@Autowired
	private ChatMessageRepository messageRep;

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

	public String createTopic(User user1, User user2) {

		String topic = null;
		Integer no1 = user1.getUserno();
		Integer no2 = user2.getUserno();

		topic = "chatRoom";
		topic += no1 < no2 ? no1.toString() + "_" + no2.toString() : no2.toString() + "_" + no1.toString();
		return topic;
	}

	@Transactional
	public ChatRoom openChatRoom(int friendno, User me) {

		User friend = userRep.findById(friendno).get();

		String topic = createTopic(me, friend);

		ChatRoom chatRoom = chatRoomRep.findByTopic(topic);

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

		return chatRoom;
	}

	@Transactional
	public int sendMessage(MessageObject mObj) {

		try {
			ChatRoom chatRoom = chatRoomRep.findByTopic(mObj.getSubscribe());

			User user = userRep.findById(mObj.getUserno()).get();
			ChatMessage message = ChatMessage.builder().chatRoom(chatRoom).text(mObj.getContent()).user(user).build();
			messageRep.save(message);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

}
