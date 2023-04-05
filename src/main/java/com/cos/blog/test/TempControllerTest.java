package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
    //http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("temphome()");
		// 파일리턴 기본 경로 :src/main/resource/static
		// 리턴 명:/home.html
		// 풀경로:src/main/resource/static/home.html
		return "/home.html";
	}
	@GetMapping("/temp/jsp")
	public String tempjsp() {
		//prefix: /WEB-INF/views/
		//suffix:.jsp
		//풀네임:/WEB-INF/views/test.jsp
		return"/test";   
	}
}
    