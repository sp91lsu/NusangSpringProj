package com.mycom.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

	private MessageType type;
	private String content;
	private String sender;
	private int userno;
	private String subscribe;

	
}