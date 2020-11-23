package com.mycom.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycom.blog.dto.Location;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.jooq.tables.JUser1;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
public class UserService extends BasicService<UserRepository,User> {


	@Autowired
	public UserService(UserRepository repository) {
		setRepository(repository);
	}

	@Autowired
	private BCryptPasswordEncoder pwEncoder;

	@Autowired
	private ChatRoomRepository chatRoomRep;

	JUser1 juser1 = JUser1.USER1;

	@Transactional
	public int signUp(User user, AuthType authType) {
		try {
			user.setRole(RoleType.USER);
			user.setAuthType(authType);
			User setUser = user.clone();
			setUser.setPassword(pwEncoder, user.getPassword());
			repository.save(setUser);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService: signUp : " + e.getMessage());
		}

		return -1;
	}

	@Transactional
	public int updateUserInfo(User user) {

		User findUser = repository.findByUsername(user.getUsername()).orElseThrow(() -> {
			return new IllegalArgumentException("updateUserInfo : dbError");
		});

		if (findUser.getAuthType() == AuthType.NORMAL) {
			findUser.setEmail(user.getEmail());
			findUser.setPassword(pwEncoder, user.getPassword());
		}

		return 1;
	}

	// 회원 존재 여부
	@Transactional
	public boolean isExsistUserName(String username) {
		User findUser = null;
		try {
			findUser = repository.findByUsername(username).get();
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(findUser);
			JsonNode jsonNode = mapper.readTree(json);

			System.out.println("찾아냈구나 " + jsonNode.toPrettyString());
		} catch (Exception e) {
			System.out.println("아이디 음슴");
		}
		return findUser != null;
	}

	@Transactional
	public User searchNickname(String searchValue, User myUser) {
		System.out.println("searchValue " + searchValue);
		User findUser = repository.findByNickname(searchValue);

		if (findUser != null) {
			/*
			 * ChatRoom room = ChatRoom.builder().user(myUser).chatUser(findUser).build();
			 * ChatRoom room2 = ChatRoom.builder().user(findUser).chatUser(myUser).build();
			 */
			/*
			 * chatRoomRep.save(room); chatRoomRep.save(room2);
			 */
		}
		return findUser;
	}

	public List<User> findNearUserList() {

		Location location = conAssist.getUser().getLocation();
		List<User> nearUserList = new ArrayList<User>();
		try {
			nearUserList = repository.calc_distance(location.getLatitude(), location.getLongtitude(),
					location.getView_distance());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("근처 회원 없음");
		}
		return nearUserList;
	}

//	@Transactional(readOnly = true) //정합성 유지
//	public User1 login(User1 user) {
//		return userRep.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
//	

}
