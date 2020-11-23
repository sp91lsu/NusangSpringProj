package com.mycom.blog.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.mycom.blog.controller.assist.ConAssist;

@Aspect
@Component
public class ControllerAop {
	Logger logger = LoggerFactory.getLogger(ControllerAop.class);

	// BookService의 모든 메서드 execution(* com.*.*(..))
	@Autowired
	ConAssist conAssist;

	/*
	 * @Around("execution(* com.mycom.blog.controller.UserController.*(..))") public
	 * Object logging(ProceedingJoinPoint pjp) throws Throwable {
	 * logger.info("요청 경로 - " + pjp.getSignature().getDeclaringTypeName() + " / " +
	 * pjp.getSignature().getName()); Object result = pjp.proceed(); return result;
	 * }
	 */
	@Pointcut("execution(* com.mycom.blog.controller.ShopController.*(..))")
	private void ShopController() {
	}

	@Pointcut("execution(* com.mycom.blog.controller.api.PaymentApiController.*(..))")
	private void PaymentApiController() {
	}

	@Pointcut("within(com.mycom.blog.controller.assist..*)")
	private void conAssistPackage() {
	}

	@Pointcut("within(com.mycom.blog.controller..*)")
	private void controllerPackage() {
	}

	@Pointcut("execution(* com.mycom.blog.controller.FriendController.*(..))")
	private void friendController() {
	}

	@Pointcut("execution(* com.mycom.blog.controller.api.FriendController_API.*(..))")
	private void friendController_API() {
	}

	@Pointcut("execution(* com.mycom.blog.controller.BoardController.*(..))")
	private void boardController() {
	}

	@Pointcut("within(com.mycom.blog.controller..*)")
	private void loginAfter() {
	}

	@Around("friendController() || friendController_API() || ShopController() "
			+ "|| PaymentApiController()")
	public Object chkUpdateUser(ProceedingJoinPoint pjp) throws Throwable {
		
				Object result = pjp.proceed();
		if (conAssist.getUser() != null) {
			conAssist.updateUser();
			System.out.println("AOP 유저 정보 갱신 - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());

		}
		
		return result;
	}

}
