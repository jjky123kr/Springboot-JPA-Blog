package com.cos.blog.Controller.api;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.Config.auth.PrincipalDetail;
import com.cos.blog.Config.auth.PrincipalDetailService;
import com.cos.blog.DTO.ResponseDto;
import com.cos.blog.Service.UserService;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;

@RestController
public class UserApiController {
  @Autowired
  private UserService userService;
  
  @Autowired
  private AuthenticationManager authenticationManager; // 얘를 통해 세션값 변경해야함

  
  //회원가입 로직
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController:save 호출");
		// 실제로 DB에 insert 실행 하고 아래에서 return 한다. 	 
	   userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바 오브젝트를 JSON으로 변환해서 리턴 (Jackson)
	}

// 회원수정 
	@PutMapping("/user")
	public ResponseDto<Integer>update(@RequestBody User user){ // key=value, x-www-form-urlencoded
		System.out.println("UserApiController:update호출");
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
		// 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.
		// 세션 등록

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
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
