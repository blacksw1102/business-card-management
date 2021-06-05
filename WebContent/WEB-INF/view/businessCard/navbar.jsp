<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	.navbar-brand {
		font-size: 30px;
		font-weight: bold;
	}
	
	.nav-item {
		margin-top: 30px;
		padding: 15px;
		border: 1px solid white;
		border-radius: 10px;
		background-color: white;
	}
	
	.nav-link {
		color: black !important;
		font-size: 30px;
		font-weight: bold;
	}
</style>
<nav class="navbar navbar-dark fixed-top bg-dark">
	<a class="navbar-brand" href="/businessCardList">명함관리</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="true" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>		
	</button>
	<div class="navbar-collapse collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<a class="nav-link" href="/businessCardWrite">명함등록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/logout">로그아웃</a>
			</li>
		</ul>
	</div>
</nav>
