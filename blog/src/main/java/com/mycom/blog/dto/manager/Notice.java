package com.mycom.blog.dto.manager;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
private int no;
private String title;
private String contents;
private String name;
private Date regdate;

}
