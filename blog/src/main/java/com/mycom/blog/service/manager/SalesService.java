package com.mycom.blog.service.manager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.blog.dto.manager.Payment;
import com.mycom.blog.model.PayMonth;
import com.mycom.blog.repository.manager.PaymentRepository;

@Service
public class SalesService {

	@Autowired
	PaymentRepository paymentRepository;

	public Map<String, BigDecimal> searchMonthPay(String dateSelect) {
		System.out.println("년도 맞나요!!??" + dateSelect.split("-")[0]);
		String[] arr =  dateSelect.split("-");
		String yearMonth = arr[0] + arr[1];
		Map<String, BigDecimal> payMap = new HashMap<String, BigDecimal>();
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

}
