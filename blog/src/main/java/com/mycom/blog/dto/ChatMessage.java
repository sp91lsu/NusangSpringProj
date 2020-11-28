package com.mycom.blog.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor

@SequenceGenerator(name = "CHAT_MESSAGE_ROOM_SEQ_GEN", // 시퀀스 제너레이터 이름
		sequenceName = "CHAT_MESSAGE_ROOM_SEQ", // 시퀀스 이름
		initialValue = 1, // 시작값
		allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
)
@Builder
@Table(name = "CHAT_MESSAGE_ROOM")
@DynamicInsert // insert 시에 null인 필드 는 제외시킴
@Entity // user클래스가 자동으로 테이블을 생성s
@ToString(exclude = {"chatRoom","user"}) 
@JsonIdentityInfo(generator = IntSequenceGenerator.class, property = "id")
public class ChatMessage {

	@Id // primarykey
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_MESSAGE_ROOM_SEQ_GEN")
	private int messageno; // 시퀀스 auto_increment

	@ManyToOne
	@JoinColumn(name = "roomno" )
	private ChatRoom chatRoom;
	
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "userno")
	private User user;
	
	@Temporal(value = TemporalType.DATE)
	@CreationTimestamp
	private Date createDate;
	
	
	public String getFormatStr() {
		SimpleDateFormat format = new SimpleDateFormat ( "HH:mm");
		String timeStr = format.format(createDate);
		return timeStr;
	}
	
	
}
