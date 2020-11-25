package com.mycom.blog.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.FriendType;
import com.mycom.blog.dto.enumtype.GenderType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.jooq.tables.records.JFriendRecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor

@SequenceGenerator(name = "USER_SEQ_GEN", // 시퀀스 제너레이터 이름
		sequenceName = "USER_SEQ", // 시퀀스 이름
		initialValue = 1, // 시작값
		allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
)
@Builder
@Table(name = "User1")
@DynamicInsert // insert 시에 null인 필드 는 제외시킴
@Entity // user클래스가 자동으로 테이블을 생성
@ToString(exclude = "location")
@JsonIdentityInfo(generator = IntSequenceGenerator.class, property = "id")
public class User {

	@Id // primarykey
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
	private int userno; // 시퀀스 auto_increment

	@Enumerated(EnumType.STRING)
	private AuthType authType;

	@Column(nullable = false, length = 100, unique = true)
	private String userid;

	@Column(nullable = false, length = 100, unique = true)
	private String username;

	@Column(nullable = false, length = 100, unique = true)
	private String nickname;

	private int age;

	@Enumerated(EnumType.STRING)
	private GenderType gender;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;

	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다.

	@Column(nullable = false)
	@CreationTimestamp // 시간이 자동입력
	private Timestamp createDate;

	@OneToMany(mappedBy = "me")
	private List<ChatRoomGuide> chatRoomGuideList;

	@OneToOne
	@JoinColumn(name = "locationno")
	private Location location;

	private String picture;

	private int coin;

	@OneToMany(mappedBy = "me", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Friend> friendList = new ArrayList<Friend>();

	private int availableTalk;
	
	/*
	 * @OneToMany(mappedBy = "userno" ,fetch = FetchType.LAZY)
	 * 
	 * @JsonIgnoreProperties({"friends"}) private List<User> friends;
	 */

	public void setPassword(BCryptPasswordEncoder encoder, String password) {
		setPassword(encoder.encode(password));
	}

	@Override
	public User clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return User.builder().userno(this.userno).userid(this.userid).nickname(this.username).username(this.username)
				.password(this.password).email(this.email).role(this.role).authType(this.authType).build();
	}

	public boolean availableReqFriend(User user) {

		if (userno == user.getUserno() || friendList == null)
			return false;

		for (int i = 0; i < friendList.size(); i++) {
			if (friendList.get(i).getUser().getUserno() == user.getUserno()) {
				return false;
			}
		}

		return true;
	}

	public boolean isMyFriend(User user) {

		if (nickname.equals(user.getNickname()))
			return true;

		for (Friend myFriend : friendList) {
			if (myFriend.getFriendType() == FriendType.REALATIONSHIP
					&& myFriend.getUser().nickname.equals(user.getNickname())) {
				return true;
			}
		}
		return false;
	}

	public List<Friend> friend_reqList(boolean fromMe) {

		List<Friend> list = new ArrayList<Friend>();

		for (Friend addFriend : friendList) {
			if (addFriend.getFriendType() == FriendType.REQUEST
					&& fromMe == (addFriend.getFromWho().getUserno() == ConAssist.getUserno())) {
				list.add(addFriend);
			}
		}
		return list;
	}

	public boolean isMe(User user) {
		if (user.userno == ConAssist.getUserno()) {
			return true;
		}
		return false;
	}
}
