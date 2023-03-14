package com.cos.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//인증이 안된 사용자들이 출입할 수 있는 경로 /auth/** 허용
// 그냥 주소가 / 이면, index.jsp 허용
//static 이하에 있는 /js/**,/css/**,/image/**

@Controller
public class UserController {

	//회원가입 폼
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return"user/joinForm";
	}
	// 로그인 폼
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return"user/loginForm";
	}
	// 회원정보 
	
}
