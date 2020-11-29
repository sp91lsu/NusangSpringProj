package com.mycom.blog.repository.manager;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.blog.dto.manager.FAQ;

public interface QNARepository extends JpaRepository<FAQ, Integer> {

}
