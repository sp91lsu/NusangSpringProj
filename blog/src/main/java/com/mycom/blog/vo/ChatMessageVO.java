package com.mycom.blog.vo;

import lombok.Data;

@Data
public class ChatMessageVO {

	String text;
	String topic;
	int userno;
	String nickname;
	String  createDate;
	String matchUser;
	String view_cnt;
}
