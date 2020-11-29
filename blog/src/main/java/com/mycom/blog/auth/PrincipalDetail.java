package com.mycom.blog.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.mycom.blog.dto.User;

import lombok.Getter;
import lombok.Setter;

//로그인 오브젝트를 스프링 시큐리티에 등록하기 위한 클래스 
@Getter
@Setter
public class PrincipalDetail implements UserDetails,OAuth2User {

	private User user; // 콤포지션
	Map<String, Object> attributesMap;

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserid();
	}

	// 계정 만료여부
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정잠금여부
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	// 비밀번호 만료여부 (true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정 활성화 여부
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	@Override
	public Map<String, Object> getAttributes() {
		return attributesMap;
	}	
	// 계정 권한여부 계정권한 추가시 add
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collectors = new ArrayList<GrantedAuthority>();

		collectors.add(() -> {
			return "ROLE_" + user.getRole();
		});
		return collectors;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
