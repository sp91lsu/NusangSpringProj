package com.mycom.blog.repository.manager;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.dto.manager.Payment;
import com.mycom.blog.model.PayMonth;


//DAO 
//자동으로 빈등록 가능 

public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	
	
	@Modifying
	@Query(value = "SELECT " + 
			"    TO_CHAR(regTime, 'yyyymm') reg," + 
			"    sum(pay) pay" + 
			" FROM payment" +
			" where TO_CHAR(regTime, 'yyyy') like ?1 " +
			" GROUP BY " + 
			" TO_CHAR(regTime, 'yyyymm')" + 
			" order by reg asc", nativeQuery = true)
	List<PayMonth> searchYear(String yearMonth);
	
	@Modifying
	@Query(value = "select * from payment where TO_CHAR(regTime, 'YYYY-MM') like ?1", nativeQuery = true)
	List<Payment> searchMonth(String mon);
	
}
