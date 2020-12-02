package com.mycom.blog.vo;

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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.Board;
import com.mycom.blog.dto.ChatRoomGuide;
import com.mycom.blog.dto.Friend;
import com.mycom.blog.dto.Location;
import com.mycom.blog.dto.Wish;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.FriendType;
import com.mycom.blog.dto.enumtype.GenderType;
import com.mycom.blog.dto.enumtype.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserVO {

	private int userno; // 시퀀스 auto_increment

	private AuthType authType;

	private String userid;

	private String username;

	private String nickname;

	private int age;

	private GenderType gender;

	private String password;

	private String email;

	private RoleType role = RoleType.USER; // Enum을 쓰는게 좋다.

	private Timestamp createDate;

	private List<ChatRoomGuide> chatRoomGuideList;

	private Location location;

	private String picture;

	private int coin = 0;

	private List<Friend> friendList = new ArrayList<Friend>();

	private List<Board> boardList = new ArrayList<Board>();

	private List<Wish> wishList = new ArrayList<Wish>();

	private Integer availableTalk = 3;

}
