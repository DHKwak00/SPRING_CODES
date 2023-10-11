package edu.spring.ex04.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/member")
public class LoginController {
	private static final Logger logger =
			LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/login")
	public void loginGET() {
		logger.info("loginGET() 호출");
	}
	
	@PostMapping("/login")
	public String loginPOST(String memberId, String password, String targetURL, HttpServletRequest request) {
		logger.info("loginPOST() 호출");
		if(memberId.equals("test") && password.equals("1234")) {
			logger.info("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("memberId", memberId);
			session.setMaxInactiveInterval(60);
			
			if(!targetURL.equals("")) {
				return "redirect:" + targetURL;
			} else {
				return "redirect:/board/list";
			}
			
		} else {
			logger.info("로그인 실패 : targetURL = " + targetURL);
			if(targetURL != null) {
				try {
					targetURL = URLEncoder.encode(targetURL, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:/member/login?targetURL=" + targetURL;				
			} else {
				return "redirect:/member/login";
			}
		}
	} // end loginPOST()
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		logger.info("logout() 호출");
		HttpSession session = request.getSession();
		if(session.getAttribute("memberId") != null) {
			session.removeAttribute("memberId");
			
			return "redirect:/board/list";
		} else {
			return "redirect:/board/list";
		}
	} // end logout()
}











