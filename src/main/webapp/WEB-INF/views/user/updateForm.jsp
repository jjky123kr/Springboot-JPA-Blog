<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<main>

	<form action="/user/join" method="post">
     <input type="hidden" id="id" value="${principal.user.id}"/>
		<div class="mb-3 mt-3">
			<label for="username" class="form-label">Username </label> 
			<input type="text"  value="${principal.user.username}"class="form-control" id="username" placeholder="Username" name="username" readonly="readonly">
		</div>
		
		<c:if test="${empty principal.user.oauth}">
		<div class="mb-3">
			<label for="password" class="form-label">Password </label> 
			<input type="password"value="${principal.user.password}" class="form-control" id="password" placeholder="Password" name="password">
		</div>
		</c:if>
		
		<div class="mb-3">
			<label for="email" class="form-label">Email </label> 
			<input type="email" value="${principal.user.email}" class="form-control" id="email" placeholder="Email" name="email" readonly="readonly">
		</div>
	
	</form>
	<!-- 회원가입 시 버튼으로 js 실행 id="btn-save" 호출-->
		<button id="btn-update" class="btn btn-primary">회원수정 완료</button>
</main>
<!-- user.js 실행 코드 -->
<script src="/js/user/user.js">


</script>

<%@ include file="../layout/footer.jsp"%>
