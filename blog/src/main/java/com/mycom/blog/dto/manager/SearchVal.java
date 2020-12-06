package com.mycom.blog.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchVal {
	private String no_min;
	private String no_max;
	private String name;
	private String id;
	private String pw;
	private String nic;
	private String age_min;
	private String age_max;
	private String gender;
	private String email;
	private String coin_min;
	private String coin_max;
	private String tPay_min;
	private String tPay_max;
	private String talk_min;
	private String talk_max;
	private String date_min;
	private String date_max;
	private String roll;
	
}
