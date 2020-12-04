package com.mycom.blog.controller.api;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.ChatMessage;
import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.model.Response;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.service.ChatRoomService;
import com.mycom.blog.service.UserService;
import com.mycom.blog.vo.ChatRoomVO;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@RestController
@RequestMapping(value = { "/api/chat" })
public class ChatApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private ChatRoomService chatRoomService;

	@Autowired
	private ConAssist conAssist;
//	@Autowired
//	HttpSession session;

	@PostMapping("/update_read_message")
	public Response<List<ChatMessage>> update_read_message(String topic) {

		System.out.println("update_read_message : save ");

		ChatRoom chatRoom = chatRoomService.updateRoomByTopic(topic);

		if (chatRoom != null) {
			return new Response<List<ChatMessage>>(200,chatRoom.getMessageList());
		} else {
			return new Response<List<ChatMessage>>(-1,null);
		}
	}

	@GetMapping("/updat_list_view")
	public List<ChatRoomVO> updat_list_view(String topic) throws JsonMappingException, JsonProcessingException {

		List<ChatRoomVO> chatRoomVOList = chatRoomService.getUserChatRoomList();

		return chatRoomVOList;
	}

}
