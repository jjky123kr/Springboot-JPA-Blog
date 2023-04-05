package com.cos.blog.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.server.ui.LoginPageGeneratingWebFilter;

import com.cos.blog.Config.auth.PrincipalDetailService;

// 빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는것
@Configuration // loC

public class SecurityConfig {

	@Autowired
	private PrincipalDetailService PrincipalDetailService;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

//	 프레임워크에서 제공하는 클래스 중 하나로 비밀번호를 암호화하는 데 사용할 수 있는 메서드를 가진 클래스입니다.
	@Bean
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	// 시큐리티가 대신 로그인 해주는데 password를 가로채기를 하는데
	// 해당password가 뭘로 해수가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교 할 수 있다.

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(PrincipalDetailService).passwordEncoder(encode());
	}

	@Bean
	DefaultSecurityFilterChain confiaure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()  // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
		.authorizeRequests()
			.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**") 
			.permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/auth/loginForm")
			.loginProcessingUrl("/auth/loginProc")
			.defaultSuccessUrl("/"); // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.		     
		return http.build();
	}

}
