package com.mycom.blog.controller.api;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.dto.manager.Payment;
import com.mycom.blog.model.payment.TimePay;
import com.mycom.blog.model.payment.TotPay;
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
	
	@RequestMapping("/timeChart")
	public List<TimePay> timeChart(String date1,String date2) {
		return salesService.searchTimePay(date1,date2);
	}
	
	@RequestMapping("/dateChart")
	public Map<String, BigDecimal> dateChart(String year,String month) {
		return salesService.searchDatePay(year,month);
	}
	@RequestMapping("/monthChart")
	public Map<String, BigDecimal> monthChart(String year) {
		return salesService.searchMonthPay2(year);
	}
	
	@RequestMapping("/circleChart")
	public Map<String, BigDecimal> circleChart(String date1,String date2) {
		return salesService.searchTotalPay(date1,date2);
	}
	@RequestMapping("/circleChart2")
	public Map<String, BigDecimal> circleChart2(String year,String month) {
		return salesService.searchTotalPay2(year,month);
	}
	@RequestMapping("/circleChart3")
	public Map<String, BigDecimal> circleChart3(String year) {
		return salesService.searchTotalPay3(year);
	}
}
