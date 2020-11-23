package com.mycom.blog.service;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.mycom.blog.repository.LocationRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.jooq.tables.JUser1;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
public class LocationService extends BasicService<LocationRepository,Location> {

	@Autowired
	UserRepository userRep;


	@Autowired
	public LocationService(LocationRepository repository) {
		setRepository(repository);
	}

	@Transactional
	public int setLocation(String searchValue) {

		Location location = null;
		try {
			location = kakaoBo.reqLocation(searchValue);
			location.setUser(conAssist.getUser());
			User user = userRep.findById(conAssist.getUserno()).get();
			
			if (conAssist.getUser().getLocation() == null) {
				repository.save(location);
				user.setLocation(location);
			} else {
				Location locationEntity = repository.findByUser(location.getUser());
				locationEntity.setLatitude(location.getLatitude());
				locationEntity.setLongtitude(location.getLongtitude());
				locationEntity.setName1(location.getName1());
				locationEntity.setName2(location.getName2());
				locationEntity.setName3(location.getName3());
				user.setLocation(locationEntity);
			}
			
			
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	// 반경설정
	@Transactional
	public int setView_distance(int view_distance) {

		try {
			Location location = repository.findByUser(conAssist.getUser());
			location.setView_distance(view_distance);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
