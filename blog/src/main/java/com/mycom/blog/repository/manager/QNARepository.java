package com.mycom.blog.repository.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.blog.dto.User;
import com.mycom.blog.dto.manager.FAQ;
import com.mycom.blog.dto.manager.QNA;

public interface QNARepository extends JpaRepository<QNA, Integer> {
	QNA findByAnswer(String answer);

	Page<QNA> findByMe( User user,Pageable pageable);
}
