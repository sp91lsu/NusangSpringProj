package com.mycom.blog.vo;

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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.ChatMessage;
import com.mycom.blog.dto.ChatRoomGuide;
import com.mycom.blog.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class ChatRoomVO {

	private int roomno; // 시퀀스 auto_increment

	private String topic;

	private UserVO user;

	private List<ChatRoomGuide> roomGuideList;

	private List<ChatMessage> messageList = new ArrayList<ChatMessage>();

	private Timestamp createDate;

	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm", timezone="Asia/Seoul")
	private Date updateDate;

	public int getMatchedUserNo() {

		User user = getMatchUserGuide().getMe();
		System.out.println("매칭된 유저 : " + user.getUserno());
		return user.getUserno();
	}

	public int getRemainSawCnt() {
		System.out.println(getMatchUserGuide().getSawMessageCnt() + "-" + getMyGuide().getSawMessageCnt());
		return getMatchUserGuide().getSawMessageCnt() - getMyGuide().getSawMessageCnt();

	}

	// 나
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

	public ChatRoomGuide getMatchUserGuide() {

		if (roomGuideList == null)
			return null;
		for (ChatRoomGuide guid : roomGuideList) {

			if (guid.getMe().getUserno() != ConAssist.getUserno()) {
				return guid;
			}
		}
		return null;
	}

	public String getMatchedUserName() {

		try {
			return getMatchUserGuide().getMe().getNickname();
		} catch (Exception e) {
			return "알수없는 사용자";
		}

	}
	
	public String getLastMessage() {

		return messageList.get(messageList.size() -1).getText();

	}

}
