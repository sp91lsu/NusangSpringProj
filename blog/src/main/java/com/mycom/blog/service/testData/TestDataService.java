package com.mycom.blog.service.testData;
import com.mycom.blog.service.BasicService;

import java.util.List;
import java.util.Optional;
import net.bytebuddy.utility.RandomString;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.Location;
import com.mycom.blog.dto.Reply;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.Wish;
import com.mycom.blog.dto.enumtype.GenderType;
import com.mycom.blog.model.ReplySaveReq;
import com.mycom.blog.repository.BoardRepository;
import com.mycom.blog.repository.ChatMessageRepository;
import com.mycom.blog.repository.LocationRepository;
import com.mycom.blog.repository.ReplyRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.blog.repository.WishRepository;
import com.mycom.blog.repository.manager.PaymentRepository;


//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
public class TestDataService extends BasicService<UserRepository, User> {

	@Autowired
	private PaymentRepository paymentRep;
	@Autowired
	private LocationRepository locationRep;
	
	@Autowired
	public TestDataService(UserRepository userRep) {
		setRepository(userRep);
	}

	@Transactional
	public int mkBigData(int howMany) {
		try {
			for (int i = 0; i < howMany; i++) {
				User user = new User();
				DataList dl = new DataList();
				GenderType gen = dl.randGender();
				user.setUsername(dl.randName(gen));
				user.setUserid(dl.uniqID(i));
				String pw = RandomStringUtils.randomAlphanumeric(4)+RandomStringUtils.randomNumeric(4);
				user.setPassword(pw);
				user.setNickname(dl.uniqNic(i));
				user.setAge(dl.randAge());
				user.setGender(gen);
				user.setEmail(dl.uniqID(i)+dl.randEmailSub());
				user.setCreateDate(dl.randTS(365));
				
				for (int j = 0; j < howMany/1000; j++) {
					
				}
			}
			
			System.out.println("빅데이터 생성 완료");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}


}
