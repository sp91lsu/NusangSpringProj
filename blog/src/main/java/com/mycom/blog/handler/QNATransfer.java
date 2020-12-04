package com.mycom.blog.handler;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.mycom.blog.dto.manager.QNA;
import com.mycom.blog.vo.QNAVO;
import com.mycom.blog.vo.UserVO;

public class QNATransfer {

	public static Page<QNAVO> listToPage(Pageable pageable,Page<QNA> qnaList) {
		
		List<QNAVO> voList = new ArrayList<QNAVO>();
		
		for (QNA qna : qnaList) {
			QNAVO qnaVo = new QNAVO();
			UserVO userVo = new UserVO();
		
			BeanUtils.copyProperties(qna, qnaVo); // no title contents answer regdate 
			BeanUtils.copyProperties(qna.getMe(),userVo); // user 
			
			qnaVo.setMe(userVo);
			voList.add(qnaVo);
		}
		System.out.println("qnavo 사이즈 : " + qnaList.getTotalElements());		
		return  new PageImpl<QNAVO>(voList, pageable, qnaList.getTotalElements());
	}
}
