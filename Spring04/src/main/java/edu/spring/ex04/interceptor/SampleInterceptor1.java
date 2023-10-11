package edu.spring.ex04.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor1 extends HandlerInterceptorAdapter{
	private static final Logger logger = 
			LoggerFactory.getLogger(SampleInterceptor1.class);
	
	// * Interceptor
	// - 컨트롤러에 들어오는 요청(HttpRequest)과 응답(HttpResponse)을 가로채는 역할
	// - Filter는 Spring Context 외부에서 관리되어 동작 (controller와 얼마나 가까운가)
	// - Interceptor는 DispatcherServlet과 Controller 사이에서 동작
	// - Interceptor는 Spring Context 내부에 존재
	
	// * Interceptor
	// - HandlerInterceptorAdapter를 상속받는 클래스를 생성
	// - servlet-context.xml에 bean과 url 매핑 설정
	
	// * Filter, Interceptor, AOP의 각 사용 용도에 대한 고찰
	
	// <Filter>
	// - 전체적인 request, response에 대한 처리가 필요할 때
	// - 문자 인고팅
	
	// <Interceptor>
	// - 세션 및 쿠키 체크 등 HTTP 프로토콜 단위로 처리가 필요할 때
	// - 로그인 세션 체크 등
	
	// <AOP>
	// - 비지니스(Service) 계층에 대한 세밀한 조정이 필요할 때
	// - 로깅, 트랜잭션, 예외 처리 등
	
	
	
	// * preHandle
	// - 요청(request)에 해당하는 컨트롤러 메소드가 동작하기 전에
	//	 요청을 가로채서 해야할 기능들을 작성
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("===== preHandle 호출");
		return super.preHandle(request, response, handler);
	}
	
	// * postHandle
	// - 컨트롤러 메소드가 수행된 이후에, DispatcherServlet이
	//	 view(JSP)를 처리하기 전에 해야할 기능들을 작성
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("===== postHandle 호출");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	// * afterCompletion
	// - DispatcherServlet에 의해 화면 처리(view, jsp)가
	//	 끝난 후에 해야할 기능들을 작성
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("===== afterCompletion 호출");
		super.afterCompletion(request, response, handler, ex);
	}
	

}









