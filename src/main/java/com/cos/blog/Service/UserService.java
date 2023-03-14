package com.cos.blog.Service;


import org.springframework.beans.factory.annotation.Autowired;
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

}