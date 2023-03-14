package com.cos.blog.Controller.api;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.DTO.ResponseDto;
import com.cos.blog.Service.UserService;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;

@RestController
public class UserApiController {
  @Autowired
  private UserService userService;
  
  
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController:save 호출");
		// 실제로 DB에 insert 실행 하고 아래에서 return 한다. 	 
	   userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바 오브젝트를 JSON으로 변환해서 리턴 (Jackson)
	}

	
	// 기존 로그인 방식 
	/*
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>login(@RequestBody
	 * User user,HttpSession session){ //session
	 * System.out.println("UserApiController:login 호출"); User
	 * principal=userService.로그인(user); //principal (접근주체)
	 * 
	 * if(principal !=null) { session.setAttribute("principal", principal); } return
	 * new ResponseDto<Integer>(HttpStatus.OK.value(),1); }
	 */
	
}
