package com.mycom.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplySaveReq {

	int userId;
	int boardId;
	int secretmode;
	String content;
}
