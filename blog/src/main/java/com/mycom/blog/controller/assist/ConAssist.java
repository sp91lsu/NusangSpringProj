package com.mycom.blog.controller.assist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

	public static User getUser() {

		try {
			PrincipalDetail pd = getPrincipal();
			return pd.getUser();
		} catch (Exception e) {
			return null;
		}
	}

	public static int getUserno() {
		try {
			PrincipalDetail pd = getPrincipal();
			return pd.getUser().getUserno();
		} catch (Exception e) {
			return 0;
		}

	}

	public static PrincipalDetail getPrincipal() {
		try {
			return (PrincipalDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}

	}

	// DB에서 유저 정보를 반환
	public User updateUser() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			PrincipalDetail principal = principalService.loadUserByUserno(getUserno());
			UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(principal,
					auth.getCredentials(), principal.getAuthorities());
			newAuth.setDetails(principal);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
			return getUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
