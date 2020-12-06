package com.mycom.blog.service.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.mycom.blog.dto.manager.Payment;
import com.mycom.blog.dto.manager.QNA;
import com.mycom.blog.handler.QNATransfer;
import com.mycom.blog.model.ProductPayload;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.repository.ItemRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.blog.repository.manager.PaymentRepository;
import com.mycom.blog.service.BasicService;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
public class PaymentService extends BasicService<PaymentRepository, Payment> {

	@Autowired
	public PaymentService(PaymentRepository repository) {
		setRepository(repository);
	}

	
	
}
