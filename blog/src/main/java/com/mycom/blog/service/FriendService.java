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
import com.mycom.blog.dto.enumtype.FriendType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.repository.FriendRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.jooq.tables.JFriend;
import com.mycom.jooq.tables.records.JFriendRecord;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
public class FriendService extends BasicService<Friend> {

	FriendRepository friendRep;

	@Autowired
	UserRepository userRep;

	JFriend jfriend = JFriend.FRIEND;

	@Autowired
	public FriendService(FriendRepository friendRep) {
		this.friendRep = friendRep;
		setRepository(this.friendRep);
	}

	@Transactional
	public void addFriend(int friendno, User me) {

		User friendUser = userRep.findById(friendno).get();
		Friend friend = Friend.builder().me(me).user(friendUser).build();

		conAssist.getUser();
		save(friend);
		conAssist.updateUser();
	}

	@Transactional
	public int addFriend_request(int friendno) {

		try {
			User me = conAssist.getUser();
			User friendUser = userRep.findById(friendno).get();
			Friend myfriend = Friend.builder().me(me).user(friendUser).friendType(FriendType.REQUEST).fromWho(me).build();
			Friend targetFriend = Friend.builder().me(friendUser).user(me).friendType(FriendType.REQUEST).fromWho(me).build();
			save(myfriend);
			save(targetFriend);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
