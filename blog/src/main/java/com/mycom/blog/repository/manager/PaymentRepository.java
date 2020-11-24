package com.mycom.blog.repository.manager;




import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.blog.dto.manager.Payment;


//DAO 
//자동으로 빈등록 가능 

public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	
	
}
