package com.mycom.blog.controller;

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

import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.model.ChatMessage;
import com.mycom.blog.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@Controller
public class ChatController {
	
	@Autowired
	private  SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private ChatRoomService chatRoomService;

	@MessageMapping("/chat.sendMessage")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		System.out.println(chatMessage.getSubscribe());
		simpMessagingTemplate.convertAndSend("/topic/" + chatMessage.getSubscribe(), chatMessage);
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		Map<String, Object> map =  headerAccessor.getSessionAttributes();
		map.put("username", chatMessage.getSender());
		return chatMessage;
	}

	@MessageMapping("/app/chat.leave")
	@SendTo("/topic/public")
	public ChatMessage leaveUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().remove("username");
		return chatMessage;
	}

	@GetMapping("/chat/chatpage")
	public String moveChatPage() {

		System.out.println("chat");
		return "/chat/chat";
	}

	@GetMapping("chat/go_chatroom")
	public String goChatPage(int chat_userno, @AuthenticationPrincipal PrincipalDetail principal, Model model) {

		ChatRoom chatRoom = chatRoomService.openChatRoom(chat_userno, principal.getUser());
		model.addAttribute("chatRoom", chatRoom);

		System.out.println("chat" + chatRoom.getRoomno());
		return "/chat/chat";
	}

	@GetMapping("/video/video_view")
	public String videoView() {

		System.out.println("video");
		return "/video/videoTest";
	}
	
}
