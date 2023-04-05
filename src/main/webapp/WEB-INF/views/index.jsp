<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="layout/header.jsp"%>

<main>
	<c:forEach var="board" items="${boards.content}">
		<div class="card m-5">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<p class="card-text">내용</p>
				<a href="/board/${board.id }" class="btn btn-dark">상세보기</a>
			</div>
		</div>
	</c:forEach>

<%--페이징 처리 방식 1. --%>
	<ul class="pagination justify-content-center">
  <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
    <c:forEach var="i" begin="1" end="${boards.totalPages}">
            <li class="page-item"><a class="page-link" href="?page=${i-1}">${i}</a></li>
        </c:forEach>
  <li class="page-item"><a class="page-link" href="#">Next</a></li>
</ul> 


	<%-- <ul class="pagination justify-content-center">
		<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
		<c:forEach var="i" begin="${startPageNo}" end="${endPageNo}">
			<c:choose>
				<c:when test="${i eq boards.number+1}">
					<li class="page-item active"><a class="page-link" href="?page=${i -1}">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${i -1}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<li class="page-item"><a class="page-link" href="#">Next</a></li>
	</ul> --%>
	
	

</main>
<br />


<%@ include file="layout/footer.jsp"%>
