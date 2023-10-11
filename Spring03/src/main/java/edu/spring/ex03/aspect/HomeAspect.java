package edu.spring.ex03.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // Proxy 객체 주입(injection)하기 위해 선언
@Aspect    // Aspect = Advice + Piontcut
public class HomeAspect {
	private static final Logger logger = 
			LoggerFactory.getLogger(HomeAspect.class);
	
	// * Aspect
	// - 일반적으로 메소드에 특정 기능을 적용시킴
	// - 메소드 실행 전후에 특정 기능을 적용시킬 수 있음
	
	// * Pointcut을 지정하는 방법
	// 1. @Before, @After, ... 어노테이션 안에서 지정
	// 2. @Pointcut 어노테이션을 안에서 지정
	// - 동일한 Pointcut이 반복되는걸 피할 수 있음
	// - 한 번 지정한 Pointcut을 여러 advice 메소드에 참조
	
	@Pointcut("execution(* edu.spring.ex03.HomeController.home(..))")
	public void pcHome() {} // 포인트컷 위치 지정
	
	@Around("pcHome()")
	public Object homeAdvice(ProceedingJoinPoint jp) {
		Object result = null;
		
		logger.info("===== homeAdvice");
		
		try {
			logger.info("===== home() 호출 전"); // @Before
			result = jp.proceed(); // HomeController.home() 실행
			logger.info("===== home() 리턴 후"); // @AfterReturning
		} catch (Throwable e) {
			// @AfterThrowing
			logger.info("===== 예외 발생 : " + e.getMessage());
		} finally {
			logger.info("===== finally");
		}
		
		return result;
	}

}
