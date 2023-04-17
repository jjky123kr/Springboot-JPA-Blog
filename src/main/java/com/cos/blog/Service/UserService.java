package com.cos.blog.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
// 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	  private BCryptPasswordEncoder encoder;
	

	
		
// 회원 가입
	@Transactional
	public void 회원가입(User user) {
		
		String rawPassword = user.getPassword();// 1234원문
		String encPassword = encoder.encode(rawPassword); // 해쉬	
		user.setPassword(encPassword); // 해쉬 변경
		user.setRole(RoleType.USER); 
		userRepository.save(user);
	}
	/*
	 * @Transactional(readOnly = true) //select 할때 트랜잭션이 시작, 서비스 종료시 트랜잭션 종료(정합성)
	 * public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */


	// 회원수정
	@Transactional
	public void 회원수정(User user) {
	   // 수정시에는 영속성 컨텍스트 User오브젝트를 영속화 시키고, 영속성User오브젝트를 수정한다. 
		// select 를 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화 시키기 위해서 
		// 영속화된 오브젝트를 변경하면서 자동으로 DB에 update 문을 날려준다. 
		User persistance =userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		if(persistance.getOauth()==null ||persistance.getOauth().equals("")) {
			
			String rawPassword =user.getPassword();
		     String encPassword=encoder.encode(rawPassword);
		     persistance.setPassword(encPassword);
		     persistance.setEmail(user.getEmail());
		}
          
     //userRepository.save(persistance);
     // 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit 이 자동으로 됩니다. 
     // 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어서 , update문을 자동으로 입력한다. 
	}

  // 회원찾기
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}

}
