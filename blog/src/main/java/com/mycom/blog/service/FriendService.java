package com.mycom.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.auth.PrincipalDetailService;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.dto.Friend;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.dto.simple.SimpleUser;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.repository.FriendRepository;
import com.mycom.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
public class FriendService extends BasicService<Friend> {

	FriendRepository friendRep;

	@Autowired
	UserRepository userRep;

	@Autowired
	ConAssist conAssist;

	@Autowired
	public FriendService(FriendRepository friendRep) {
		this.friendRep = friendRep;
		setRepository(this.friendRep);
	}

	public void addFriend(int friendno, User me) {

		User friendUser = userRep.findById(friendno).get();
		Friend friend = Friend.builder().me(me).user(friendUser).build();
		save(friend);
		conAssist.updateUser();
	}

}
