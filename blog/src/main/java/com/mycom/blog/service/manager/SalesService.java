package com.mycom.blog.service.manager;

import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.blog.model.payment.DatePay;
import com.mycom.blog.model.payment.PayMonth;
import com.mycom.blog.model.payment.TimePay;
import com.mycom.blog.model.payment.TotPay;
import com.mycom.blog.repository.manager.PaymentRepository;

@Service
public class SalesService {

	@Autowired
	PaymentRepository paymentRepository;

	public Map<String, BigDecimal> searchMonthPay(String dateSelect) {
		System.out.println("년도 맞나요!!??" + dateSelect.split("-")[0]);
		String[] arr =  dateSelect.split("-");
		String yearMonth = arr[0] + arr[1];
		Map<String, BigDecimal> payMap = new LinkedHashMap<String, BigDecimal>();
		DecimalFormat df = new DecimalFormat("00");
		for (int i = 1; i <= 12; i++) {
			payMap.put(arr[0] + df.format(i),  new BigDecimal(0));
		}
		List<PayMonth> list = paymentRepository.searchYear(arr[0]);
		for (PayMonth payMonth : list) {
			payMap.put(payMonth.getReg(), payMonth.getPay());
		}
		return payMap;
	}
	
	public Map<String, BigDecimal> searchTotalPay(String date1,String date2) {
		Map<String, BigDecimal> payMap = new LinkedHashMap<String, BigDecimal>();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dDate2 = null;
		try {
			dDate2 = transFormat.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dDate2.setDate(dDate2.getDate()+1);
		String sDate2 = transFormat.format(dDate2);
		List<TotPay> list = paymentRepository.sumPay(date1, sDate2);
		for (TotPay totPay : list) {
			payMap.put(totPay.getGender(), totPay.getTotpay());
		}
		return payMap;
	}
	public Map<String, BigDecimal> searchTotalPay2(String year,String month) {
		Map<String, BigDecimal> payMap = new LinkedHashMap<String, BigDecimal>();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("00");
		String yearmonth = year+df.format(Integer.parseInt(month));
		List<TotPay> list = paymentRepository.sumPay(yearmonth);
		for (TotPay totPay : list) {
			payMap.put(totPay.getGender(), totPay.getTotpay());
		}
		return payMap;
	}
	public Map<String, BigDecimal> searchTotalPay3(String year) {
		Map<String, BigDecimal> payMap = new LinkedHashMap<String, BigDecimal>();
		List<TotPay> list = paymentRepository.sumPay_year(year);
		for (TotPay totPay : list) {
			if(totPay.getGender()!=null)
			payMap.put(totPay.getGender(), totPay.getTotpay());
		}
		return payMap;
	}
	
	public List<TimePay> searchTimePay(String date1,String date2) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dDate2 = null;
		try {
			dDate2 = transFormat.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dDate2.setDate(dDate2.getDate()+1);
		String sDate2 = transFormat.format(dDate2);
		List<TimePay> list = paymentRepository.timePay(date1, sDate2);
		return list;
	}

	public Map<String, BigDecimal> searchDatePay(String year,String month) {
		Map<String, BigDecimal> payMap = new LinkedHashMap<String, BigDecimal>();
		DecimalFormat df = new DecimalFormat("00");
		String yearmonth = year+df.format(Integer.parseInt(month));
		List<DatePay> list = paymentRepository.datePay(yearmonth);
		for (DatePay datePay : list) {
			payMap.put(datePay.getRegdate(), datePay.getPay());
		}
		return payMap;
	}
	public Map<String, BigDecimal> searchMonthPay2(String year) {
		Map<String, BigDecimal> payMap = new LinkedHashMap<String, BigDecimal>();
		List<DatePay> list = paymentRepository.monthPay(year);
		for (DatePay datePay : list) {
			payMap.put(datePay.getRegdate(), datePay.getPay());
		}
		return payMap;
	}
}
