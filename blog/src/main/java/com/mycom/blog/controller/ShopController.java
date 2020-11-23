package com.mycom.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mycom.blog.service.ShopService;


@Controller
public class ShopController {

	@Autowired
	private ShopService shopService;

	
}
