package com.cos.blog.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;


@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
			

	@Transactional
	public void 글작성(Board board, User user) { // title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}


}
