package edu.spring.ex03.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	private static final Logger logger = 
			LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution(* edu.spring.ex03.service.CustomerServiceImple.*Customer(..))")
	public void beforeAdvice(JoinPoint jp) {
		logger.info("===== beforeAdvice");
		logger.info("target : " + jp.getTarget());
		logger.info("signature : " + jp.getSignature());
	}
	
} // end LogginAspect
