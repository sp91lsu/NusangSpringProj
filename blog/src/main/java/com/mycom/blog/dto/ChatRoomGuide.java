package com.mycom.blog.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor

@SequenceGenerator(name = "CHAT_ROOM_GUIDE_SEQ_GEN", // 시퀀스 제너레이터 이름
		sequenceName = "CHAT_ROOM_GUIDE_SEQ", // 시퀀스 이름
		initialValue = 1, // 시작값
		allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
)
@Builder
@Table(name = "CHAT_ROOM_GUIDE")
@DynamicInsert // insert 시에 null인 필드 는 제외시킴
@Entity // user클래스가 자동으로 테이블을 생성s
@ToString(exclude = {"me","chatRoom"})
@JsonIdentityInfo(generator = IntSequenceGenerator.class, property = "id")
public class ChatRoomGuide {

	@Id // primarykey
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_ROOM_GUIDE_SEQ_GEN")
	private int no; // 시퀀스 auto_increment

	@ManyToOne(fetch = FetchType.LAZY)
	private User me;

	@ManyToOne(fetch = FetchType.LAZY)
	private ChatRoom chatRoom;
	
	private int sawMessageCnt;

	@CreationTimestamp
	private Date updateDate;
}
