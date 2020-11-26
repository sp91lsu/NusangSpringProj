package com.mycom.blog.dto.manager;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import com.mycom.blog.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor

@SequenceGenerator(name = "FAQ_SEQ_GEN",
				   sequenceName = "FAQ_SEQ",
				   initialValue = 1,
				   allocationSize = 1)

@Builder
@Table(name = "FAQ")
@DynamicInsert
@Entity
@JsonIdentityInfo(generator = IntSequenceGenerator.class, property = "id")
public class FAQ {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FAQ_SEQ_GEN")
	private int no;
	private String title;
	@Column(length = 20000)
	private String contents;
	
	

}
