package com.mycom.blog.service.testData;
import com.mycom.blog.service.BasicService;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.GenderType;
import com.mycom.blog.dto.enumtype.PayType;
import com.mycom.blog.dto.enumtype.Price_Coin;
import com.mycom.blog.dto.manager.Payment;
import com.mycom.blog.repository.UserRepository;
import com.mycom.blog.repository.manager.PaymentRepository;


//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
public class TestDataService extends BasicService<UserRepository, User> {

	@Autowired
	private PaymentRepository paymentRep;
	
	@Autowired
	public TestDataService(UserRepository userRep) {
		setRepository(userRep);
	}

	@Transactional
	public int mkBigData(int uRowCnt,int pRowCnt,int uDateRange,int pDateRange) {
		try {
			DataList dl = new DataList();
			//USER bigdata
			for (int i = 0; i < uRowCnt; i++) {
				//DataList의 샘플데이터 메소드들과 랜덤함수를 활용하여 데이터 다양화.
				User user = new User();
				GenderType gen = dl.randGender();
				user.setUsername(dl.randName(gen));
				user.setUserid(dl.uniqID(i));
				String pw = RandomStringUtils.randomAlphanumeric(4)+RandomStringUtils.randomNumeric(4);
				user.setPassword(pw);
				user.setNickname(dl.uniqNic(i));
				user.setAge(dl.randAge());
				user.setGender(gen);
				user.setEmail(dl.uniqID(i)+"@"+dl.randEmailSub());
				repository.save(user);
//				User lastRecord = repository.lastRecord();
			}
			System.out.println("USER Datas Insert 완료");
			
			List<User> userList = repository.findAll();
			for (User user : userList) {
				user.setCreateDate(dl.randTS(uDateRange));
			}
			System.out.println("userList setRegtime 완료");
			
			//PAYMENT bigdata
			for (int i = 0; i < pRowCnt; i++) {
				Payment pay = new Payment();
				//유저 랜덤픽
				Random r = new Random();
				int size = userList.size();
				User randUser = userList.get(r.nextInt(size));
				
				//랜덤유저가 price를 지급하고 코인을 사는 과정
				Price_Coin pc = dl.randPrice_Coin();
				pay.setPay(pc.getPrice());
				randUser.setCoin(randUser.getCoin()+pc.getCoin());
				randUser.setTotalPay(randUser.getTotalPay()+pc.getPrice());
				
				pay.setPaytype(PayType.BUY);
				pay.setUser(randUser);
				paymentRep.save(pay);
			}
			System.out.println("PAYMENT Datas Insert 완료");
			List<Payment> payList = paymentRep.findAll();
			for (Payment payment : payList) {
				payment.setRegtime(dl.randTS(pDateRange));
			}
			System.out.println("PAYMENT setRegtime 완료");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
