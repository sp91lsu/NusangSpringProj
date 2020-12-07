package com.mycom.blog.service;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.jooq.DSLContext;
import org.jooq.TableField;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.ChatMessage;
import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.dto.ChatRoomGuide;
import com.mycom.blog.dto.User;
import com.mycom.blog.model.MessageObject;
import com.mycom.blog.repository.ChatMessageRepository;
import com.mycom.blog.repository.ChatRoomGuideRepository;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.blog.vo.ChatMessageVO;
import com.mycom.blog.vo.ChatRoomVO;
import com.mycom.blog.vo.UserVO;
import com.mycom.jooq.tables.JChatRoomGuide;
import com.mycom.jooq.tables.records.JChatRoomGuideRecord;

@Service
public class ChatRoomService extends BasicService<ChatRoomRepository, ChatRoom> {

	@Autowired
	private ChatRoomGuideRepository chatRoomGuidRep;
	@Autowired
	private ChatMessageRepository messageRep;
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private UserService userService;

	private JChatRoomGuide jChatRoomGuide = new JChatRoomGuide();

	@Autowired
	public ChatRoomService(ChatRoomRepository chatRoomRep) {
		setRepository(chatRoomRep);
	}

	@Transactional
	public int chkFriendOpenChatRoom(int friendno, boolean isMyFriend) {

		try {
			User friend = userService.findById(friendno);

			String topic = conAssist.createTopic(ConAssist.getUser(), friend);

			ChatRoom chatRoom = repository.findByTopic(topic);

			System.out.println(chatRoom);

			if (chatRoom == null) {

				// 내 친구가 아니면
				if (!isMyFriend) {
					// 말걸기 횟수를 깎고
					User user = conAssist.updateUser();
					if (!user.useAvailableTalk()) {
						// 없으면 코인을 깎는다
						if (!user.useCoin(ConAssist.useTalkCoin)) {
							// 코인도 없으면
							throw new Exception("말걸기 0 코인 0 비정상적인 요청 의심");
						}
					}

				}

				chatRoom = ChatRoom.builder().build();
				chatRoom.setTopic(topic);
				chatRoom.setFromWho(ConAssist.getUser());
				chatRoom = save(chatRoom);
				ChatRoomGuide roomGuide1 = ChatRoomGuide.builder().me(ConAssist.getUser()).chatRoom(chatRoom).build();
				ChatRoomGuide roomGuide2 = ChatRoomGuide.builder().me(friend).chatRoom(chatRoom).build();
				chatRoomGuidRep.save(roomGuide1);
				chatRoomGuidRep.save(roomGuide2);
			}

			System.out.println("채팅방 개설");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Transactional
	public ChatRoom enterTheChatRoom(int friendno, User me) {

		User friend = userService.findById(friendno);

		String topic = conAssist.createTopic(me, friend);
		ChatRoom chatRoom = updateRoomByTopic(topic,me);

		System.out.println(chatRoom);
		return chatRoom;
	}

	// 메세지 보내고 채팅 정보 갱신
	@Transactional
	public int saveMessage(ChatMessageVO mObj) {

		Date current = new Date();
		try {
			ChatRoom chatRoom = repository.findByTopic(mObj.getTopic());
			chatRoom.setUpdateDate(current);
			chatRoom.getRoomGuideList();
			User user = userService.findById(mObj.getUserno());
			chatRoom.findUserGuide(user.getUserno()).setUpdateDate(current);
			ChatMessage message = ChatMessage.builder().chatRoom(chatRoom).text(mObj.getText()).user(user)
					.createDate(current).build();
			mObj.setCreateDate(message.getFormatStr());
			messageRep.save(message);
			updateRoomByTopic(chatRoom.getTopic(),user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Transactional
	public List<ChatRoomVO> getUserChatRoomList() throws JsonProcessingException {

		List<ChatRoom> chatRoomList = repository.getUserChatRoomList(ConAssist.getUserno());

		List<ChatRoomVO> chatRoomVOList = new ArrayList<ChatRoomVO>();
		for (ChatRoom chatRoom : chatRoomList) {
			if (chatRoom.getMessageList().size() > 0 || chatRoom.getFromWho().getUserno() == ConAssist.getUserno()) {
				ChatRoomVO vo = new ChatRoomVO();
				UserVO userVo = new UserVO();
				BeanUtils.copyProperties(chatRoom, vo);
				BeanUtils.copyProperties(chatRoom.getMatchedUser(), userVo);
				vo.setUser(userVo);
				chatRoomVOList.add(vo);
			}
		}

		return chatRoomVOList;
	}

	@Transactional
	public ChatRoom updateRoomByTopic(String topic,User user) {

		try {
			ChatRoom chatRoom = repository.findByTopic(topic);
			Date update = new Date();
			chatRoom.setUpdateDate(update);
			chatRoom.getRoomGuideList();
			chatRoom.findUserGuide(user.getUserno()).setUpdateDate(update);
			chatRoom.findUserGuide(user.getUserno()).setSawMessageCnt(chatRoom.getMessageList().size());
			chatRoom.chkMessageCnt();
			List mList = chatRoom.getMessageList();
			int size = mList.size();
			if (size > 200) {
				chatRoom.setMessageList(mList.subList(size - 199, size));
			}
			
			
			return chatRoom;
		} catch (Exception e) {
			return null;
		}
	}

}
