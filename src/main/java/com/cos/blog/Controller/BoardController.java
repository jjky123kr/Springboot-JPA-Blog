package com.cos.blog.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.Config.auth.PrincipalDetail;

@Controller
public class BoardController {

	// index 페이지
	@GetMapping("/")
	public String index() { //컨트롤러에서 어떻게 찾는가?
		// /WEB-INF/views/index.jsp
		return"index";
	}
   // User 권한이 필요
	 @GetMapping("board/writeForm")
	 public String saveForm() {
		 return"board/writeForm";
	 }
}
  