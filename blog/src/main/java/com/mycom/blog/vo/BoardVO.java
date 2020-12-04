package com.mycom.blog.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import com.mycom.blog.controller.assist.ConAssist;
import com.mycom.blog.dto.Reply;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.Wish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {

	private int id;

	private String title;

	private String content;

	private int count;

	private UserVO user;

	private List<Reply> replyList;

	private Timestamp createDate;

	private int viewcnt;

	private int heartcnt;

	private List<Wish> wishList = new ArrayList<Wish>();

	public boolean isWishBoard(User user) {

		for (Wish wish : wishList) {
			if (wish.getMe().getUserno() == ConAssist.getUserno()) {
				return true;
			}
		}
		return false;
	}

}
