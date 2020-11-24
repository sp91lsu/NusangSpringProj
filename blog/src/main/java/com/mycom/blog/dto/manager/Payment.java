package com.mycom.blog.dto.manager;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.PayType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "PAYMENT_SEQ_GEN", // 시퀀스 제너레이터 이름
		sequenceName = "PAYMENT_SEQ", // 시퀀스 이름
		initialValue = 1, // 시작값
		allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
)
@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_SEQ_GEN")
	private int paymentno;
	
	@CreationTimestamp
	private Timestamp regtime;
	
	private int pay;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userno")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private PayType paytype;
	
	
	
}
