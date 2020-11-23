package com.mycom.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.ApplicationScope;

import com.mycom.blog.dto.Shop;
import com.mycom.blog.dto.enumtype.ItemType;
import com.mycom.blog.service.ShopService;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@GetMapping(value = "/shop_view")
	public String shop_view(Model model) {
		
		Shop coin_shop =  shopService.findShop(ItemType.COIN);
		model.addAttribute("coin_shop", coin_shop);
		return "/payment/shop";
	}
}
