package com.mycom.blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycom.blog.dto.User;
import com.mycom.blog.repository.UserRepository;

//로그인검증을  위한 클래스 
@Service //빈등록
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRep;
	//스프링이 로그인 요청을 가로챌 떄 username,password 두개를 가로챌 때 
	//password 부분처리느 ㄴ알아서함 
	//username이 db에 있는지 확인해야 함
	@Override
	public PrincipalDetail loadUserByUsername(String userid) throws UsernameNotFoundException {
	
		User principal = userRep.findByUserid(userid)
				.orElseThrow(()-> {return new UsernameNotFoundException("유저id를 찾을 수 없음 : " + userid); });
	
		return new PrincipalDetail(principal);
	
	}

	
}
