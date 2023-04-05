package com.cos.blog.Controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.Config.auth.PrincipalDetail;
import com.cos.blog.DTO.ResponseDto;
import com.cos.blog.Service.BoardService;
import com.cos.blog.model.Board;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	// 글작성
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.글작성(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
 // 글 삭제
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteByid(@PathVariable int id) {
		boardService.글삭제(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

	}
// 글 수정 
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer>update(@PathVariable int id , @RequestBody Board board){
		boardService.글수정(id,board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
