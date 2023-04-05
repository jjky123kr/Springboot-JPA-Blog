package com.cos.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.Config.auth.PrincipalDetail;
import com.cos.blog.Service.BoardService;

@Controller
public class BoardController {

	@Autowired 
	public BoardService boardService;
	
	//컨트롤에서 센션을 어떻게 찾는가? 
	// @AuthenticationPrincipal PrincipalDetail principal
	@GetMapping("/")
	public String index(Model model,@PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC)Pageable pageable) { 
		
		model.addAttribute("boards", boardService.글목록(pageable));
		
//		 페이징 처리하기
//		 int startPage = ((pageable.getPageNumber()-1) / 10) * 10 + 1;
//	        pageable.getPageSize();
//	        int endPage = startPage + 10 - 1  > pageable.getPageSize() ? pageable.getPageSize() : startPage + 10 - 1;
//	        model.addAttribute("startPageNo", startPage);
//	        model.addAttribute("endPageNo", endPage);	
//	                
		return"index";
	}
	
	// 글 상세보기
	@GetMapping("board/{id}")
	public String findByid(@PathVariable int id , Model model) {
		
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/detail";
		
	}
	// 글 수정 폼
	@GetMapping("board/{id}/updateForm")
	public String updateForm(@PathVariable int id , Model model) {
		
		model.addAttribute("board", boardService.글상세보기(id));
		
		return"board/updateForm";
	}
	
	
   // User 권한이 필요
	 @GetMapping("board/writeForm")
	 public String saveForm() {
		 return"board/writeForm";
	 }
}
  