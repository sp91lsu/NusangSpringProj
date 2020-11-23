package com.mycom.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mycom.blog.dto.Location;
import com.mycom.blog.dto.User;

//DAO 
//자동으로 빈등록 가능 

public interface LocationRepository extends JpaRepository<Location, Integer> {

	Location findByUser(User user);

}
