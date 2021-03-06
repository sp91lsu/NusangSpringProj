package com.mycom.blog.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageObject {

	private MessageType type;
	private String content;
	private String sender;
	private int userno;
	private String subscribe;
	private String formatDateStr;
	
}