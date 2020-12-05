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
	public void big(@RequestParam("howmany") String howMany,Model model) {
		int c = Integer.parseInt(howMany);
		int result = testDataService.mkBigData(c);
		model.addAttribute("result",result);
	}
}
