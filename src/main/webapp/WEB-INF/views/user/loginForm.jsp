<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<main>

	<form action="/auth/loginProc" method="post">
		<div class="mb-3 mt-3">

			<label for="username" class="form-label">
			Email: 
			</label> <input type="text" class="form-control" id="username" placeholder="Username" name="username">
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">
			Password: </label> 
			<input type="password" class="form-control" id="password" placeholder="Password" name="password">
		</div>


		<div class="form-check mb-3">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox" name="remember"> Remember me
			</label>
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=77ccaa86becdc3e0d53eb721fe3db52b
		&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code">
		<img height="38px;" src="/image/kakao_login_button.png" /></a>
	</form>

</main>  

<!-- user.js 실행 코드
<script src="/js/user/user.js"></script> -->

<%@ include file="../layout/footer.jsp"%>

