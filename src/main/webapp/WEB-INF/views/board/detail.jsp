<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

     <br/><br/>

<div class="container">

	<h3>${board.title}</h3>

	<div>${board.content }</div>
	
	<div>
	글번호 :<span id="id"><i>${board.id }</i></span>
	작성자 :<span><i>${board.user.username} </i></span>
	</div>
	
    <br/><br/>

	<div>
	<c:if test="${board.user.id==principal.user.id}">
		<a href="/board/${board.id}/updateForm" class="btn btn-dark">글수정</a>
		<button id="btn-delete" class="btn btn-dark">글삭제</button>
       </c:if>
		<button  class="btn btn-dark" onclick="history.back()">목록</button>
	</div>

</div>

<!-- board.js -->
<script src="/js/board/board.js"></script>

<%@ include file="../layout/footer.jsp"%>