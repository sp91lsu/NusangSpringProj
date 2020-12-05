package com.mycom.blog.controller.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.dto.manager.Payment;
import com.mycom.blog.model.PayMonth;
import com.mycom.blog.service.manager.SalesService;

@RestController
@RequestMapping("api/sales")
public class SalesApiController {

	@Autowired
	SalesService salesService;
	
	
	@RequestMapping("/monSales")
	public Map<String, BigDecimal> monSales(String dateSelect) {
		System.out.println("날짜 나오니?? "+dateSelect);
		Map<String, BigDecimal> list = salesService.searchMonthPay(dateSelect);
		return list;
	}
}
