package com.mycom.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycom.blog.dto.Item;
import com.mycom.blog.dto.Location;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.PayType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.dto.manager.Payment;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.repository.ItemRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.blog.repository.manager.PaymentRepository;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
public class UserService extends BasicService<UserRepository, User> {

	@Autowired
	ItemRepository itemRep;

	@Autowired
	PaymentRepository paymentRep;
	
	@Autowired
	public UserService(UserRepository repository) {
		setRepository(repository);
	}

	@Autowired
	private BCryptPasswordEncoder pwEncoder;

	@Autowired
	private ChatRoomRepository chatRoomRep;


	@Transactional
	public int signUp(User user) {
		try {
			user.setPassword(pwEncoder, user.getPassword());
			repository.save(user);

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

	@Transactional
	public List<User> findNearUserList() {

		Location location = conAssist.getUser().getLocation();
		List<User> nearUserList = new ArrayList<User>();
		try {
			nearUserList = repository.getNearUserList(location.getLatitude(), location.getLongtitude(),
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
	@Transactional
	public int buyCoin(Map<String, Object> jsonMap) {

		try {
			System.out.println(jsonMap.get("imp_uid"));
			System.out.println(jsonMap.get("merchant_uid"));
			System.out.println(jsonMap.get("status"));

			String[] payLoad = jsonMap.get("merchant_uid").toString().split("_");

			int userno = Integer.parseInt(payLoad[0]);
			int itemno = Integer.parseInt(payLoad[1]);
			String merchant_uid = payLoad[2];

			User user = findById(userno);
			Item item = itemRep.findById(itemno).get();

			int totalCoin = user.getCoin() + item.getNum();
			user.setCoin(totalCoin);

			Payment payment = Payment.builder()
					.imp_uid(merchant_uid)
					.pay(100)
					.paytype(PayType.BUY)
					.user(user).build();
			paymentRep.save(payment);
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}

	@Scheduled(cron = "0 0 * * * *")
	public void availableTalkUpdate() {
		System.out.println("gg");
	}
	
	@Transactional
	public User searchNickname(String nickname) {
		User findUser = repository.findByNickname(nickname);

		return findUser;
	}
}
