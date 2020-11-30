package com.mycom.blog.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import com.mycom.blog.controller.assist.ConAssist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor

@SequenceGenerator(name = "CHAT_ROOM_SEQ_GEN", // 시퀀스 제너레이터 이름
		sequenceName = "CHAT_ROOM_SEQ", // 시퀀스 이름
		initialValue = 1, // 시작값
		allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
)
@Builder
@Table(name = "CHAT_ROOM")
@DynamicInsert // insert 시에 null인 필드 는 제외시킴
@Entity // user클래스가 자동으로 테이블을 생성s
@ToString(exclude = { "roomGuideList" })
@JsonIdentityInfo(generator = IntSequenceGenerator.class, property = "id")
public class ChatRoom {

	@Id // primarykey
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_ROOM_SEQ_GEN")
	private int roomno; // 시퀀스 auto_increment

	private String topic;

	@OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
	private List<ChatRoomGuide> roomGuideList;

	@OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("createDate asc")
	private List<ChatMessage> messageList = new ArrayList<ChatMessage>();

	@CreationTimestamp
	private Timestamp createDate;

	private Date updateDate;

	public User getMatchedUser() {

		if (roomGuideList == null)
			return null;
		for (ChatRoomGuide guid : roomGuideList) {

			if (guid.getMe().getUserno() != ConAssist.getUserno()) {
				return guid.getMe();
			}
		}
		return null;
	}
	
	//나
	public ChatRoomGuide getMyGuide() {

		if (roomGuideList == null)
			return null;
		for (ChatRoomGuide guid : roomGuideList) {

			if (guid.getMe().getUserno() == ConAssist.getUserno()) {
				return guid;
			}
		}
		return null;
	}

	

	public String getMatchedUserName() {

		if (roomGuideList == null)
			return null;
		for (ChatRoomGuide guid : roomGuideList) {

			if (guid.getMe().getUserno() != ConAssist.getUserno()) {
				return guid.getMe().getNickname();
			}
		}
		return "알수없는 사용자";
	}

}
