package edu.spring.ex04.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor2 extends HandlerInterceptorAdapter{
	private static final Logger logger = 
			LoggerFactory.getLogger(SampleInterceptor2.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("===== preHandle 호출");
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		// url 경로에 있는 bean 객체
		logger.info("Bean : " + handlerMethod.getBean());
		// url 경로에 있는 method 객체
		logger.info("method : " + method.getName());
		return super.preHandle(request, response, handler); // (= return true;)
//		return false;
		
		// preHandle() 리턴값의 의미
		// - true : 원래 실행하려고 했던 컨트롤러 메소드 실행
		// - false : 컨트롤러 메소드를 실행하지 않음
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("===== postHandle 호출");
		// * ModelAndView
		// - interceot된 경로에 있는 model과 view의 정보를 가져옴
		String data = (String) modelAndView.getModel().get("data");
		logger.info("data : " + data);
		modelAndView.getModel().put("data", "intercepted");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	

}









