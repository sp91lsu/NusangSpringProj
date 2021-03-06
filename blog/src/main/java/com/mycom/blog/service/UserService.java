package com.mycom.blog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycom.blog.dto.Item;
import com.mycom.blog.dto.Location;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.PayType;
import com.mycom.blog.dto.manager.Payment;
import com.mycom.blog.dto.manager.QNA;
import com.mycom.blog.dto.manager.SearchVal;
import com.mycom.blog.handler.QNATransfer;
import com.mycom.blog.model.ProductPayload;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.repository.ItemRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.blog.repository.UserSpecs;
import com.mycom.blog.repository.UserSpecs.SearchKey;
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

	@Transactional
	public Page<User> findAll_ASCUserno(Pageable pageable) {

		Page<User> list = null;
		try {
			list = repository.findAllSortByUserno(pageable);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return list;
	}
	
	@Transactional
	public List<User> findBySearchValues(SearchVal sv) {

		List<User> list = new ArrayList<User>();
		try {
//			list = repository.findBySearchValues(sv);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return list;
	}
	
	@Transactional
	public List<User> searchedList(Map<String, Object> searchRequest) {
		//정렬정보 객체
		Sort sort = Sort.by("age").descending()
				  .and(Sort.by("gender").descending())
				  .and(Sort.by("username").ascending())
				  ;
		//서치 조건들을 쿼리문에 적용해서 리스트 받아오기
		List<User> list = null;
		try {
			Map<SearchKey, Object> searchKeyMap = new HashMap<>();
		    for (String key : searchRequest.keySet()) {
		    	System.out.println("mapkey(form-name):"+key+" /mapVal(keyword):"+searchRequest.get(key)+" /enum키:"+SearchKey.valueOf(key.toUpperCase()));
		        searchKeyMap.put(SearchKey.valueOf(key.toUpperCase()), searchRequest.get(key));
		    }
		    list = searchKeyMap.isEmpty()
		            ? repository.findAll()
		            : repository.findAll(UserSpecs.searchWith(searchKeyMap));
		} catch (Exception e) {
			e.getStackTrace();
		}
		return list;
	}
	
	@Transactional
	public int signUp(User user) {
		try {
			user.setPassword(pwEncoder, user.getPassword());
			user = repository.save(user);
			
			user.setNickname(user.getNickname() + "_" + user.getUserno());
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
			System.out.println("근처 회원 없음");
		}
		return nearUserList;
	}

	@Transactional
	public int buyCoinWebHook(Map<String, Object> jsonMap) {

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

			Payment payment = Payment.builder().imp_uid(merchant_uid).pay(100).paytype(PayType.BUY).user(user).build();
			paymentRep.save(payment);

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Transactional
	public int buyCoin(ProductPayload payload) {

		try {

			User user = findById(payload.getUserno());
			Item item = itemRep.findById(payload.getItemno()).get();

			int totalCoin = user.getCoin() + item.getNum();
			user.setCoin(totalCoin);

			Payment payment = Payment.builder().imp_uid(payload.getMerchant_uid()).pay(100).paytype(PayType.BUY)
					.user(user).build();
			paymentRep.save(payment);

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Transactional
	@Scheduled(cron = "0 0 0 * * *")
	public void availableTalkUpdate() {
		repository.updateAvailableTalk();
		System.out.println("모든 유저 말걸기 갯수 초기화");
	}

	@Transactional
	public User searchNickname(String nickname) {
		User findUser = repository.findByNickname(nickname);

		return findUser;
	}

	@Transactional
	public User findUserId(String userid) {
		User findUser = repository.findByUserid(userid).orElseGet(() -> {
			return null;
		});

		return findUser;
	}
	
	
}
