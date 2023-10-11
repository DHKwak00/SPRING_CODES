package edu.spring.ex04.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = 
			LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("===== preHandle 호출");
		
		// 로그인 상태(세션 존재) : mapping된 URL의 컨트롤러 메소드 실행
		// 로그이웃 상태(세션 없음) : login url로 이동, 목표 url 저장
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		if(memberId != null) {
			logger.info("로그인 상태 -> controller method 실행");
			return true;
		} else {
			logger.info("로그아웃 상태 -> controller method 실행 안됨");
			// 목표 url 정보를 추출(query string이 존재하면 같이 추출)
			String targetURL = saveDestination(request);
			response.sendRedirect("/ex04/member/login?targetURL=" + targetURL);
			return false;
		}
		
	}

	private String saveDestination(HttpServletRequest request) {
		logger.info("saveDestination() 호출");
	
		String uri = request.getRequestURI();
		logger.info("요청 URI : " + uri);
		
		String contextRoot = request.getContextPath();
		
		uri = uri.replace(contextRoot, ""); // contextRoot 제거
		String queryString = request.getQueryString();
		logger.info("쿼리 스트링 : " + queryString);
		
		String targetURL = "";
		if(queryString == null) {
			targetURL = uri;
		} else {
			targetURL = uri + "?" + queryString;
		}
		
		logger.info("targetURL : " + targetURL);
		
		try {
			targetURL = URLEncoder.encode(targetURL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return targetURL;
	}
}
