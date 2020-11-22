package com.mycom.blog.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAop {
    Logger logger =  LoggerFactory.getLogger(ControllerAop.class);
    
    //BookService의 모든 메서드 execution(* com.*.*(..))

    @Around("execution(* com.mycom.blog.controller.UserController.*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("요청 경로 - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
        Object result = pjp.proceed();
        return result;
    }
}


