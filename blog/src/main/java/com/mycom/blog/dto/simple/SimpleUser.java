package com.mycom.blog.dto.simple;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

public class SimpleUser {

	@Id // primarykey
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
	protected int userno; // 시퀀스 auto_increment

	@Enumerated(EnumType.STRING)
	protected AuthType authType;

	@Column(nullable = false, length = 100, unique = true)
	protected String userid;

	@Column(nullable = false, length = 100, unique = true)
	protected String username;

	@Column(nullable = false, length = 100, unique = true)
	protected String nickname;

	@Column(nullable = false, length = 100)
	protected String password;

	@Column(nullable = false, length = 50)
	protected String email;

	@Enumerated(EnumType.STRING)
	protected RoleType role; // Enum을 쓰는게 좋다.

	@CreationTimestamp // 시간이 자동입력
	protected Timestamp createDate;

	/*
	 * public UserDto clone() throws CloneNotSupportedException { // TODO
	 * Auto-generated method stub
	 * 
	 * return
	 * UserDto.builder().userno(this.userno).userid(this.userid).nickname(this.
	 * username).username(this.username)
	 * .password(this.password).email(this.email).role(this.role).authType(this.
	 * authType).build(); }
	 */
	
	public void setPassword(BCryptPasswordEncoder encoder, String password) {
		setPassword(encoder.encode(password));
	}

	public SimpleUser clone() throws CloneNotSupportedException {
		return SimpleUser.builder().userno(this.userno).userid(this.userid).nickname(this.username).username(this.username)
				.password(this.password).email(this.email).role(this.role).authType(this.authType).build();
	}
}
