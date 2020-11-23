package com.mycom.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSession;

import org.jooq.DSLContext;
import org.jooq.impl.TableImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.auth.PrincipalDetailService;
import com.mycom.blog.bo.KakaoBo;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.ChatRoom;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.repository.UserRepository;
import com.mycom.jooq.tables.JFriend;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
public class BasicService<T, E> {

	@Autowired
	EntityManagerFactory emf;

	protected T repository;

	@Autowired
	protected DSLContext dsl;

	@Autowired
	protected KakaoBo kakaoBo;

	@Autowired
	protected ConAssist conAssist;

	public void setRepository(T repository) {
		this.repository = repository;
	}

	@Transactional
	public E findById(int id) {
		JpaRepository<E, Integer> t = (JpaRepository<E, Integer>) repository;
		E e = t.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("findbyid Error");
		});
		return e;
	}

	@Transactional(readOnly = true)
	public List<E> findAll() {
		JpaRepository<E, Integer> t = (JpaRepository<E, Integer>) repository;
		List<E> list = t.findAll();

		return list;
	}

	@Transactional
	public int deleteById(int id) {
		try {
			JpaRepository<E, Integer> t = (JpaRepository<E, Integer>) repository;
			t.deleteById(id);
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Transactional
	public E save(E entity) {
		JpaRepository<E, Integer> t = (JpaRepository<E, Integer>) repository;
		return t.save(entity);
	}

}
