<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.semi.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Re See : Recipe</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
	
	<script src="https://kit.fontawesome.com/f74c934ec8.js" crossorigin="anonymous"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
	<link rel="stylesheet" href="resources/css/common/header.css">
</head>


	<header>
		<div id="wrap_header">
			<div id="header">
				<div id="header_1">
					<!-- 관리자 등급이면 관리자 페이지 버튼 활성화 - HL -->
					<c:if
						test="${ (sessionScope.loginMember ne null) and (sessionScope.loginMember.memGrade eq 4) }">
						<a href="hllogin.ma" id="managerCheck"> 관리자 페이지 </a>
					</c:if>
				</div>
				<div id="header_2">
					<a href="${pageContext.request.contextPath}"> <img
						src="resources/css/common/Re see recipe 로고.png" alt="Reseerecipe메인">
					</a>
				</div>
				<div id="header_3">
					<div id="header_3_1">
						<div id="header_3_1_1">
							<a href="#"> <i class="fa-solid fa-cart-shopping"></i><br>
								<img src="" alt="">장바구니
							</a>
						</div>
						<div id="header_3_1_2">
							<c:choose>
								<c:when test="${ sessionScope.loginMember eq null }">
									<a href="yrloginForm.me"> <i
										class="fa-regular fa-user"></i><br> <img src="" alt="">로그인
									</a>
								</c:when>
								<c:otherwise>
									<a href="yrlogout.me"> <i
										class="fa-regular fa-user"></i><br> <img src="" alt="">로그아웃
									</a>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="header_3_1_3">
							<c:choose>
								<c:when test="${ sessionScope.loginMember eq null }">
								<a href="yrenrollForm.me">
									<i class="fa-regular fa-handshake"></i><br> <img src="" alt="">회원가입
								</a>
								</c:when>
								<c:otherwise>
									<a href="blog.me"> <i
										class="fa-regular fa-handshake"></i><br> <img src="" alt="">블로그
										<br>
									</a>
								</c:otherwise>
							</c:choose>
	
							
						</div>
						<div id="header_3_1_4">
							<a href="hllist.no"> <i
								class="fa-regular fa-door-open"></i><br> <img src="" alt="">고객센터
							</a>
						</div>
					</div>
					<div id="header_3_2">
						<form action="search.do" method="get" id="search_form">
							<div id="search_text">
								<i class="fa-solid fa-magnifying-glass" style="color: #ffde59;"></i><input
									type="text" name="query">
							</div>
							<div id="search_btn">
								<input type="submit" value="검색">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- nav영역 -->
	<nav>
		<div id="wrap_navigator">
			<div id="navigator">
				<ul id="navi">
					<li><a
						href="selectRecipeList.re?currentPage=1">레시피</a>
					</li>
					<li><a href="">셰프</a></li>
					<li><a href="main.po">마켓</a></li>
				</ul>
			</div>
		</div>
	
	</nav>



	<script src="resources/common/header.js"></script>
</html>