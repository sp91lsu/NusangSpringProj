package com.mycom.blog.repository.manager;




import java.math.BigDecimal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blog.dto.manager.Payment;
import com.mycom.blog.model.payment.DatePay;
import com.mycom.blog.model.payment.DayPay;
import com.mycom.blog.model.payment.PayMonth;
import com.mycom.blog.model.payment.TimePay;
import com.mycom.blog.model.payment.TotPay;


//DAO 
//자동으로 빈등록 가능 

public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	
	//월별_승환
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
	
	//시간대별
	@Query(value = "SELECT TO_CHAR(regtime, 'HH24')time, sum(pay)pay "
			+ "FROM payment "
			+ "WHERE "
			+ "regtime  >= TO_DATE(?1, 'yyyy/mm/dd') "
			+ "AND regtime < TO_DATE(?2,'yyyy/mm/dd') "
			+ "GROUP BY TO_CHAR(regtime, 'HH24') "
			+ "ORDER BY TO_CHAR(regtime, 'HH24') "
			, nativeQuery = true)
	List<TimePay> timePay(String date1,String date2);
	
	
	//일자별
	@Query(value = "SELECT to_char(regtime, 'YYYY/MM/DD')regdate, sum(pay)pay "
			+ "FROM payment "
			+ "WHERE "
			+ "TO_CHAR(regTime, 'yyyymm') = ?1 "
			+ "GROUP BY to_char(regtime, 'YYYY/MM/DD') "
			+ "ORDER BY to_char(regtime, 'YYYY/MM/DD') "
			, nativeQuery = true)
	List<DatePay> datePay(String yearMonth);
	
	//월별
	@Query(value = "SELECT to_char(regtime, 'YYYY/MM')regdate, sum(pay)pay "
			+ "FROM payment "
			+ "WHERE "
			+ "TO_CHAR(regTime, 'yyyy') = ?1 "
			+ "GROUP BY to_char(regtime, 'YYYY/MM') "
			+ "ORDER BY to_char(regtime, 'YYYY/MM') "
			, nativeQuery = true)
	List<DatePay> monthPay(String year);
	
	
	//요일별
	@Query(value = "SELECT to_char(regtime, 'dy')day, sum(pay)pay "
			+ "FROM payment "
			+ "WHERE "
			+ "regtime  >= TO_DATE(?1, 'yyyy/mm/dd') "
			+ "AND regtime < TO_DATE(?2,'yyyy/mm/dd') "
			+ "GROUP BY TO_CHAR(regtime, 'dy') "
			+ "ORDER BY DECODE ( day, '일', 1, '월', 2, '화', 3, '수', 4, '목', 5, '금', 6, '토', 7)"
			, nativeQuery = true)
	List<DayPay> dayPay(String date1,String date2);
	
	//성 별
	@Query(value = "select u.gender gender,sum(p.pay) totpay "
			+ " from payment p,user1 u"
			+ " where p.regtime >= to_timestamp(?1, 'YYYY-MM-DD') "
			+ " and p.regtime < to_timestamp(?2, 'YYYY-MM-DD')"
			+ " and p.userno = u.userno"
			+ " GROUP by u.gender "
			+ "	order by u.gender desc"
			, nativeQuery = true)
	List<TotPay> sumPay(String date1,String date2);
	
	//성 별2
	@Query(value = "select u.gender gender,sum(p.pay) totpay "
			+ " from payment p,user1 u"
			+ " where"
			+ " TO_CHAR(p.regTime, 'yyyymm') = ?1 "
			+ " and p.userno = u.userno"
			+ " GROUP by u.gender "
			+ "	order by u.gender desc"
			, nativeQuery = true)
	List<TotPay> sumPay(String yearmonth);
	//성 별3_year
	@Query(value = "select u.gender gender,sum(p.pay) totpay "
			+ " from payment p,user1 u"
			+ " where"
			+ " TO_CHAR(p.regTime, 'yyyy') = ?1 "
			+ " and p.userno = u.userno"
			+ " GROUP by u.gender "
			+ "	order by u.gender desc"
			, nativeQuery = true)
	List<TotPay> sumPay_year(String year);
	
	@Modifying
	@Query(value = "select * from payment where TO_CHAR(regTime, 'YYYY-MM') like ?1", nativeQuery = true)
	List<Payment> searchMonth(String mon);
	
}
