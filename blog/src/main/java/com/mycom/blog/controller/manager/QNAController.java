package com.mycom.blog.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycom.blog.dto.User;
import com.mycom.blog.dto.manager.QNA;
import com.mycom.blog.service.manager.QNAService;

@Controller
@RequestMapping("/manager/QNA")
public class QNAController {
	@Autowired
	QNAService qnaService;

//	@RequestMapping("/qnaList")
//	public String qnaList(Model model) {
//		System.out.println("리스트 탔니?");
//		List qnaList = qnaService.findAll();
//		model.addAttribute("qnaList", qnaList);
//		return "/manager/FAQ/faqList";
//	}
	@RequestMapping(value = "/qnaWriteOk", method = RequestMethod.POST)
	public String qnaWriteOk(QNA qna, User user, Model model) {
		System.out.println("글쓰기 탔니???");
		qna.setMe(user);
		qna = qnaService.save(qna);
		int res = qna != null ? 1 : 0;
		System.out.println("res값 : " + res);
		model.addAttribute("res", res);
		return "/manager/QNA/qnaWriteOk";
	}

	@RequestMapping(value = "/qnaUpdateOk", method = RequestMethod.POST)
	public String qnaUpdateOk(QNA updateQNA,User user, Model model) {
		System.out.println("답변달기 탔니????");
		updateQNA.setMe(user);
		int res = qnaService.updateOk(updateQNA);
		System.out.println("내용:"+updateQNA.getAnswer());
		model.addAttribute("res", res);
		return "manager/QNA/qnaUpdateOk";
	}

}
