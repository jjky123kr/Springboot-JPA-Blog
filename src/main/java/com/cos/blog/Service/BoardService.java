package com.cos.blog.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
			

	// 글작성
	@Transactional
	public void 글작성(Board board, User user) { // title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
  // 글 목록  
	@Transactional(readOnly=true)
	public Page<Board>글목록(Pageable pageable){
	return 	boardRepository.findAll(pageable);
	}
	
// 글 상세 페이지
	@Transactional(readOnly=true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글작성 실패:id를 찾을수 없습니다.");
				});
	}
	
// 글 삭제
	@Transactional
	public void 글삭제 (int id) {
		System.out.println("글삭제하기:"+id);
	    boardRepository.deleteById(id);
	}
// 글수정 
	
	@Transactional
	public void 글수정(int id ,Board requsetBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글작성 실패:id를 찾을수 없습니다.");
	    });		// 영속화 하기
		board.setTitle(requsetBoard.getTitle());
		board.setContent(requsetBoard.getContent());
		//해당 함수 종효시 (service)가 종료될때 트랜잭션이 종료됩니다.
		// 이때 더티채팅 - 자동 업데이트가 된다.(flush)
	}

}
