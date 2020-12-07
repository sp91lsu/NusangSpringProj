package com.mycom.blog.controller.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycom.blog.service.testData.TestDataService;

@Controller
@RequestMapping("/testdata/")
public class TestDataInsert {
	@Autowired
	private TestDataService testDataService;
	
	@GetMapping("insert")
	public void big(
			@RequestParam("uRowCnt") String uRowCnt,
			@RequestParam("pRowCnt") String pRowCnt,
			@RequestParam("uDateRange") String uDateRange,
			@RequestParam("pDateRange") String pDateRange,
			Model model) {
		int a = Integer.parseInt(uRowCnt);
		int b = Integer.parseInt(pRowCnt);
		int c = Integer.parseInt(uDateRange);
		int d = Integer.parseInt(pDateRange);
		int result = testDataService.mkBigData(a,b,c,d);
		model.addAttribute("result",result);
	}
}
