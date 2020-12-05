package com.mycom.blog.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sales")
public class SalesController {
	
	@RequestMapping("/")
	public String salesMain() {
		
		return "manager/sales/salesMain";
	}
}
