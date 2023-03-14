<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<main>

	<form action="/user/join" method="post">

		<div class="mb-3 mt-3">
			<label for="username" class="form-label">Username </label> 
			<input type="text" class="form-control" id="username" placeholder="Username" name="username">
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">Password </label> 
			<input type="password" class="form-control" id="password" placeholder="Password" name="password">
		</div>

		<div class="mb-3">
			<label for="email" class="form-label">Email </label> 
			<input type="email" class="form-control" id="email" placeholder="Email" name="email">
		</div>
	</form>
	<!-- 회원가입 시 버튼으로 js 실행 id="btn-save" 호출-->
		<button id="btn-save" class="btn btn-primary">회원가입</button>
</main>
<!-- user.js 실행 코드 -->
<script src="/js/user/user.js">


</script>

<%@ include file="../layout/footer.jsp"%>
