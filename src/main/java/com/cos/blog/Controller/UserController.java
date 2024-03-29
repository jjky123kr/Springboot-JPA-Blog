package com.cos.blog.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.Service.UserService;
import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//인증이 안된 사용자들이 출입할 수 있는 경로 /auth/** 허용
// 그냥 주소가 / 이면, index.jsp 허용
//static 이하에 있는 /js/**,/css/**,/image/**

@Controller
public class UserController {

	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	  private AuthenticationManager authenticationManager; // 얘를 통해 세션값 변경해야함
	
	
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
	// 카카오 로그인 
		@GetMapping("/auth/kakao/callback")
		public String kakaoCallback(String code) { //Data를 리턴해주는 컨트롤 함수
		// POST 방식으로 key=value 데이터를 요청한다.(카카오톡쪽으로)
		// Retrofit2
		//okHttp
		//RestTemplate
			
			
			// HttpHeader 오브젝트 생성
			RestTemplate rt=new RestTemplate();
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8" );
			
			//HttpBody 오브젝트 생성 
			MultiValueMap<String, String>params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", "77ccaa86becdc3e0d53eb721fe3db52b");
			params.add("redirect_uri","http://localhost:8000/auth/kakao/callback");
			params.add("code", code);
			
	      //HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
			HttpEntity<MultiValueMap<String, String>>kakaoTokenRequest=
					new HttpEntity<>(params,headers);
		// Http요청하기 - post방식으로 - 그리고 response 변수의 응답 받는다.
			ResponseEntity<String>response=rt.exchange(
					"https://kauth.kakao.com/oauth/token",
					HttpMethod.POST,
					kakaoTokenRequest,
					String.class
					);
		   //받아온 토큰 객체로 저장한다. 
			ObjectMapper objectMapper = new ObjectMapper();
			OAuthToken oauthToken=null;
			
			try {
			 oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
			} catch (JsonMappingException e) {
				
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
			}
			
			System.out.println("카카오엑세스 토큰"+oauthToken.getAccess_token());
			
			// 객체로 저장한 토큰 정보(access_token)을 통해 사용자 프로필을 요청한다. 
			RestTemplate rt2 = new RestTemplate();
			
			// HttpHeader 오브젝트 생성
			HttpHeaders headers2 = new HttpHeaders();
			headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
			headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
			HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
					new HttpEntity<>(headers2);
			
			// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
			ResponseEntity<String> response2 = rt2.exchange(
					"https://kapi.kakao.com/v2/user/me",
					HttpMethod.POST,
					kakaoProfileRequest2,
					String.class
			);
			System.out.println(response2.getBody());
			
			ObjectMapper objectMapper2 = new ObjectMapper();
			KakaoProfile kakaoProfile=null;
			
			try {
				kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
			} catch (JsonMappingException e) {
				
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
			}
				
			//User 오브젝트 : username, password,email
			System.out.println("카카오아이디번호:"+kakaoProfile.getId());
			System.out.println("카카오이메일:"+kakaoProfile.getKakao_account().getEmail());
			
			System.out.println("블로그서버 유저네임:"+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
			System.out.println("블로그서버 이메일:"+kakaoProfile.getKakao_account().getEmail());
			
			// UUID 중복되지 않는 어떤 특정 값을 만들떄 사용한다. 
			//UUID garbagePassword=UUID.randomUUID();
			System.out.println("블로그 서버 패스워드:"+cosKey);
			
			User kakaoUser = User.builder()
					.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
					.email(kakaoProfile.getKakao_account().getEmail())
					.oauth("kakao")
					.password(cosKey)
					.build();
			
			
			// 가입자 혹은 비가입자 체크 해서 처리
			User originUser = userService.회원찾기(kakaoUser.getUsername());
            System.out.println("가입자 찾기 Controller  확인");
			
			if(originUser.getUsername() == null) {
				System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
				userService.회원가입(kakaoUser);
			}
			System.out.println("자동 로그인을 진행합니다.");
			
			// 로그인 처리
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			return "redirect:/";
		}
		
	// 회원정보 
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return"user/updateForm";
	}
	
}
