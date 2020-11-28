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
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.ChatMessage;
import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.model.MessageObject;
import com.mycom.blog.service.ChatRoomService;
import com.mycom.blog.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
public class ChatController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private ChatRoomService chatRoomService;

	@Autowired
	private ConAssist conAssist;
	
	@MessageMapping("/chat.sendMessage")
	public MessageObject sendMessage(@Payload MessageObject messageVO) {
		System.out.println(messageVO.getSubscribe());
		
		ChatMessage message = chatRoomService.sendMessage(messageVO);
		message = chatRoomService.findMessage(message.getMessageno());
		if (message != null) {
			simpMessagingTemplate.convertAndSend("/topic/" + messageVO.getSubscribe(), messageVO);
			messageVO.setFormatDateStr(message.getFormatStr());
			
			return messageVO;
		} else {
			messageVO.setContent("네트워크가 원활하지 않습니다.");
			return messageVO;
		}
	}
	
	@MessageMapping("/chat.addUser")
	public MessageObject addUser(@Payload MessageObject chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		Map<String, Object> map = headerAccessor.getSessionAttributes();
		map.put("username", chatMessage.getSender());
		return chatMessage;
	}

	@MessageMapping("/app/chat.leave")
	@SendTo("/topic/public")
	public MessageObject leaveUser(@Payload MessageObject chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().remove("username");
		return chatMessage;
	}

	@GetMapping("/chat/chatpage")
	public String moveChatPage(int chat_userno,Model model) {
		System.out.println("chat : " + chat_userno);
		
		ChatRoom chatRoom = chatRoomService.getChatRoom(chat_userno, conAssist.getUser());
		model.addAttribute("chatRoom", chatRoom);
		return "/chat/chat";
	}

	@GetMapping("chat/go_chatroom")
	public String goChatPage(int chat_userno, @AuthenticationPrincipal PrincipalDetail principal, Model model) {
 
		chatRoomService.openChatRoom(chat_userno, principal.getUser());
		return "redirect:/chat/chatpage?chat_userno=" +chat_userno;
	}

	@GetMapping("/video/video_view")
	public String videoView() {

		System.out.println("video");
		return "/video/videoTest";
	}

}
