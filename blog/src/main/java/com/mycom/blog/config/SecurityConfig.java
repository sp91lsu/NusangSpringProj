package com.mycom.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mycom.blog.auth.PrincipalDetailService;

@Configuration // 설정 빈등록
@EnableWebSecurity // 필터걸기 = spring security가 활성화되어있는데 설정을 여기서 하겠다
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정주소로 접근하면 권한 및 인증 미리체크하게따
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService pds;
	
	
	//manager를 어디서든 쓸수 있다. 
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean //여기서 빈등록 
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	//유저 검증을 위한 설정  : 검증할 때는 기본적으로 시큐리티가 제공하는 username and password(트큰)값이지만 
	//우리가 서비스하려는 유저로그인을 위해서 principalDetailService, principalDetail을 만들고 검증을 셋팅한다. 
	//패스워드 비교해줌 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 
		 auth.userDetailsService(pds).passwordEncoder(encodePWD());
	 }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() //csrf 토큰 비활성화(테스트용)
		.authorizeRequests().
		antMatchers("/api/paging","/payment/webhook","/auth/**","/js/**","/css/**","/image/**","/home") // auth/로 해당하는 경로를
				.permitAll() // 모두 허용하고
				.anyRequest() // 나머지 경로는
				.authenticated() // 인증이 필요하기 때문에 접근 불가합니다.
				.and() // 그리고
				.formLogin() // 인증이 필요한 경로들은 로그인 폼으로 이동 되며
				.loginPage("/auth/loginForm") // 해당경로로 이동됩니다.
				.loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 로그인 경로를 가로챈다 
				.defaultSuccessUrl("/home") //로그인 성공시 홈 경로로 간다 
				;
		}
}
