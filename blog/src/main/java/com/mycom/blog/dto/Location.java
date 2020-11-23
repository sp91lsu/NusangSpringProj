package com.mycom.blog.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
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

@SequenceGenerator(name = "LOCATION_SEQ_GEN", // 시퀀스 제너레이터 이름
		sequenceName = "LOCATION_SEQ", // 시퀀스 이름
		initialValue = 1, // 시작값
		allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
)
@Builder
@Table(name = "LOCATION")
@DynamicInsert // insert 시에 null인 필드 는 제외시킴
@Entity // user클래스가 자동으로 테이블을 생성
@ToString(exclude = "user")
@JsonIdentityInfo(generator = IntSequenceGenerator.class, property = "id")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCATION_SEQ_GEN")
	private int locationno;

	@OneToOne(mappedBy = "location")
	private User user;
	private double latitude;
	private double longtitude;
	private String name1;
	private String name2;
	private String name3;
	private String tabletype;
	@ColumnDefault("5")
	@JoinColumn(name = "view_distance")
	private int view_distance = 5;

	@CreationTimestamp
	private Timestamp createDate;
	
	public String getAddress() {
		return getName1() + " " + getName2() + " " + getName3();
	}

	public String getAddress2() {
		return getName2() + " " + getName3();
	}
}
