package com.mycom.blog.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "BOARD_SEQ_GEN", // 시퀀스 제너레이터 이름
		sequenceName = "BOARD_SEQ", // 시퀀스 이름
		initialValue = 1, // 시작값
		allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
)
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GEN")
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob
	private String content;

	private int count;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userno")
	private User user;

	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // 연관관계의 주인이 아니다
	@JsonIgnoreProperties({ "board" })
	@OrderBy("id desc")
	private List<Reply> replyList;

	@CreationTimestamp
	private Timestamp createDate;

	private int viewcnt;

	private int heartcnt;

	public String calcTime() {
		Date current = new Date(System.currentTimeMillis());

		long betweenTime = current.getTime() - createDate.getTime();
		long time = 0;

		if ((time = TimeUnit.MILLISECONDS.toDays(betweenTime)) > 0) {
			return time + "일 전";
		} else if ((time = TimeUnit.MILLISECONDS.toHours(betweenTime)) > 0) {
			return time + "시간 전";
		} else if ((time = TimeUnit.MILLISECONDS.toMinutes(betweenTime)) > 0) {
			return time + "분 전";
		} else if ((time = TimeUnit.MILLISECONDS.toSeconds(betweenTime)) > 0) {
			return time + "초 전";
		}
		return "";
	}
}
