package com.mycom.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.ChatMessage;
import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.model.MessageObject;
import com.mycom.blog.service.ChatRoomService;
import com.mycom.blog.service.FriendService;
import com.mycom.blog.service.UserService;
import com.mycom.blog.vo.ChatMessageVO;

import lombok.RequiredArgsConstructor;

@Controller
public class ChatController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private ChatRoomService chatRoomService;

	@Autowired
	private FriendService friendService;

	@Autowired
	private ConAssist conAssist;

	@MessageMapping("/chat.sendMessage")
	public void sendMessage(@Payload ChatMessageVO messageVO) {

		int result = chatRoomService.saveMessage(messageVO);
		System.out.println("메세지 저장");
		if (result == 1) {

			// 상대방에게 업데이트 요청
			simpMessagingTemplate.convertAndSend(
					"/topic/" + messageVO.getTopic() + ".update_req." + messageVO.getMatchUser(), messageVO.getTopic());

			simpMessagingTemplate.convertAndSend("/topic/" + messageVO.getTopic(), messageVO.getTopic());

			// 채팅 목록에 있는 유저에게 send
			// simpMessagingTemplate.convertAndSend("/topic/" + messageVO.getMatchUser(),
			// messageVO);
		}
	}

	@MessageMapping("/send_topic")
	public void chatBroadCast(@Payload ChatMessageVO messageVO) {

		simpMessagingTemplate.convertAndSend(messageVO.getTopic(), messageVO.getTopic());
	}

	@MessageMapping("/chat.updateMatchUser")
	public void updateTopicUser(@Payload ChatMessageVO messageVO) {
		simpMessagingTemplate.convertAndSend("/topic/" + messageVO.getMatchUser(), messageVO.getTopic());
	}

//	@MessageMapping("/chat.addUser")
//	public MessageObject addUser(@Payload MessageObject chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//		Map<String, Object> map = headerAccessor.getSessionAttributes();
//		map.put("username", chatMessage.getSender());
//		return chatMessage;
//	}
//
//	@MessageMapping("/app/chat.leave")
//	@SendTo("/topic/public")
//	public MessageObject leaveUser(@Payload MessageObject chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//		headerAccessor.getSessionAttributes().remove("username");
//		return chatMessage;
//	}

	@GetMapping("/chat/chatpage")
	public String moveChatPage(int chat_userno, Model model) {
		System.out.println("chat : " + chat_userno);

		ChatRoom chatRoom = chatRoomService.enterTheChatRoom(chat_userno, conAssist.getUser());
		simpMessagingTemplate.convertAndSend("/topic/" + chatRoom.getTopic(), chatRoom.getTopic());
		model.addAttribute("chatRoom", chatRoom);

		return "/chat/chat";
	}

	@GetMapping("chat/go_chatroom")
	public String openChatPage(int chat_userno, Model model) {

		boolean isMyFriend = friendService.isMyFriend(chat_userno);

		int result = chatRoomService.chkFriendOpenChatRoom(chat_userno, isMyFriend);

		if (result == 1) {
			return "redirect:/chat/chatpage?chat_userno=" + chat_userno;
		} else {
			model.addAttribute("error", "채팅 요청에 실패하였습니다.");
			return "/layout/error";
		}
	}

	@GetMapping("/chat/chat_list_view")
	public String chatListView(Model model) throws JsonProcessingException {

		model.addAttribute("chatRoomList", chatRoomService.getUserChatRoomList());
		return "/chat/chat_list_view";
	}

}
