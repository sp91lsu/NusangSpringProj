package com.mycom.blog.controller.assist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mycom.blog.auth.PrincipalDetail;
import com.mycom.blog.auth.PrincipalDetailService;
import com.mycom.blog.dto.User;

@Component
public class ConAssist {

	public String IP = "http://localhost:8000";

	@Autowired
	private AuthenticationManager authenticationM;

	@Autowired
	private PrincipalDetailService principalService;

	public User getUser() {
		PrincipalDetail pd = getPrincipal();
		return pd.getUser();
	}
	
	public int getUserno() {
		PrincipalDetail pd = getPrincipal();
		return pd.getUser().getUserno();
	}

	public PrincipalDetail getPrincipal() {
		return (PrincipalDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	// DB에서 유저 정보를 반환
	public User updateUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		PrincipalDetail principal = principalService.loadUserByUserno(getUserno());
		UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(principal,
				auth.getCredentials(), principal.getAuthorities());
		newAuth.setDetails(principal);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
		return getUser();
	}

	// 강제 유저 로그인 시키기
	public User setSessionUser(User user) {
		System.out.println("setLoginSession : " + user.getUsername());
		System.out.println("setLoginSession : " + user.getPassword());
		Authentication authentication = authenticationM
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserid(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return getUser();
	}
}
