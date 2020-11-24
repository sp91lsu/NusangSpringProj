package com.mycom.blog.dto.manager;

import java.sql.Timestamp;

import javax.persistence.Column;
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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.PayType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@SequenceGenerator(name = "PAYMENT_SEQ_GEN", // 시퀀스 제너레이터 이름
		sequenceName = "PAYMENT_SEQ", // 시퀀스 이름
		initialValue = 1, // 시작값
		allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
)
@Table(name = "PAYMENT")
@ToString(exclude = "user")
@Entity
@JsonIdentityInfo(generator = IntSequenceGenerator.class, property = "id")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_SEQ_GEN")
	private int paymentno;
	
	@Column(length = 50)
	private String imp_uid; //imp_123515648
	
	@Column(nullable = false)
	@CreationTimestamp
	private Timestamp regtime;
	
	@Column(nullable = false)
	private int pay;
	
	@ManyToOne
	@JoinColumn(name = "userno",nullable = false)
	private User user;
	
	@Column(nullable = false,length = 10)
	@Enumerated(EnumType.STRING)
	private PayType paytype;
}
