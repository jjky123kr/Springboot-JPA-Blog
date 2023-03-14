<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<br>
<div style="padding: 10px 120px 2px 120px;">
	<div class="container">

	<form  return="board_check()">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="Enter title" id="title">
		</div>

		<div class="form-group">
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>
	<div class="d-grid gap-2 col-6 mx-auto">
   <button id="btn-save" class="btn btn-dark">글작성완료</button>
 </div>
	
</div>

<script>
  $('.summernote').summernote({
    tabsize: 2,
    height: 300
  });
</script>
</div>


<!-- board.js -->
<script src="/js/board/board.js"></script>

<%@ include file="../layout/footer.jsp"%>