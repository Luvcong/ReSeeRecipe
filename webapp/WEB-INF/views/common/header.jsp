<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.semi.member.model.vo.Member" %>
<%
	// 메인경로	
	String contextPath = request.getContextPath();

	// 로그인한 회원
	Member loginMember = (Member)session.getAttribute("loginMember");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Re See : Recipe</title>
    <!-- jQuery import구문 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    
    <!-- 스타일 -->
    <script src="https://kit.fontawesome.com/f74c934ec8.js" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
	
	<!-- alert 스타일 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<style>
	/*****************************************/
	/* header 스타일 */
	div {
		box-sizing: border-box;
		
	}
	
	#wrap_header {
		width: 1200px;
		height: 180px;
		margin: auto;
	}
	
	#wrap_header > div {
		width: 100%;
	}
	
	#header {
		height: 100%;
	}
	
	#header>div {
		height: 100%;
		float: left;
	}
	
	#header_1, #header_3 {
		width: 30%;
	}
	
	#header_2 {
		width: 40%;
		text-align: center;
	}
	
	#header_3_1>div {
		height: 100%;
		float: left;
	}
	
	#header_3_1_1, #header_3_1_2, #header_3_1_3, #header_3_1_4 {
		width: 25%;
		text-align: center;
		padding: 6px;
		font-family: 'IBM Plex Sans KR', sans-serif;
	}
	
	#header_2 img {
		height: 100%;
	}
	
	#header_3_1 {
		height: 40%;
	}
	
	#header_3_2 {
		position: relative;
	}
	
	#search_form {
		width: 70%;
		height: 20%;
		position: absolute;
		text-align: center;
		left: 20px;
		margin-top: 25px;
	}
	
	#search_form>div {
		height: 100%;
		float: left;
	}
	
	#search_text {
		width: 90%;
	}
	
	#search_btn {
		width: 10%;
	}
	
	#search_form input {
		box-sizing: border-box;
		border: 1px solid rgb(255, 145, 77);
	}
	
	#search_form input[type=submit] {
		background-color: rgb(255, 145, 77);
	}
	
	/*****************************************/
	/* nav 스타일 */
		div {
		
		box-sizing: border-box;
	}
	
	#wrap_navigator {
		width: 1200px;
		height: 60px;
		margin: auto;
	}
	
	#wrap_navigator > div {
		width: 100%;
	}
	
	#navigator {
		height: 100%;
	}

	/* 여기부터 navi CSS */
	#navi {
		list-style: none;
		margin: 0;
		padding: 0;
		height: 100%;
	}
	
	#navi>li {
		background-color: rgb(255, 222, 89);
		float: left;
		height: 100%;
		width: 200px;
		text-align: center;
	}
	
	#navi a {
		text-decoration: none;
		width: 100%;
		height: 100%;
		display: block;
		line-height: 40px;
		font-family: 'IBM Plex Sans KR', sans-serif;
		font-size: 20px;
		padding: 5px;
	}
</style>
</head>

<!-- header영역 -->
<header>

	<div id="wrap_header">
		<div id="header">
		    <div id="header_1"> <!-- 관리자 등급이면 관리자 페이지 버튼 활성화 - HL -->
 		       <% if(loginMember != null && loginMember.getMemGrade() == 4) { %>
		              <a href="<%= contextPath %>/hllogin.ma" id="managerCheck">
		                  	관리자 페이지 
		              </a>
		       <% } %>
		    </div>
		    <div id="header_2">
		        <a href="<%= contextPath %>">
		            <img src="resources/css/common/Re see recipe 로고.png" alt="Reseerecipe메인">
		        </a>
		    </div>
		    <div id="header_3">
		        <div id="header_3_1">
		            <div id="header_3_1_1">
		                <a href="#">
		                    <i class="fa-solid fa-cart-shopping"></i><br>
		                    <img src="" alt="">장바구니 
		                </a>
		            </div>
		            <div id="header_3_1_2">
		          <% if(loginMember == null) { %>
		            <!-- 로그인 클릭 시 로그인 화면으로 이동 yr -->
		              <a href="<%= contextPath %>/yrloginForm.me">
		                  <i class="fa-regular fa-user"></i><br>
		                  <img src="" alt="">로그인 
		              </a>
		          <% } else { %>
		          	<!-- 로그인 시 로그아웃으로 변경 yr -->
		          	<a href="<%= contextPath %>/yrlogout.me">
		                  <i class="fa-regular fa-user"></i><br>
		                  <img src="" alt="">로그아웃
		          	</a>
		          <% } %>
		          </div>
		          <div id="header_3_1_3">
		              <% if(loginMember == null) { %>
		              <!-- 회원가입을 누르면 회원가입 화면으로 이동 yr -->
		              <a href="<%= contextPath %>/yrenrollForm.me">
		                  <i class="fa-regular fa-handshake"></i><br> 
		                  <img src="" alt="">회원가입
		              </a>
		              <% } else { %>
		                  <a href="<%= contextPath %>/blog.me">
		                  <i class="fa-regular fa-handshake"></i><br> 
		                  <img src="" alt="">블로그 <br>
							</a>
		              <%} %>
		          </div>
		          <div id="header_3_1_4">
		              <a href="<%= contextPath %>/hllist.no">
		                  <i class="fa-regular fa-door-open"></i><br>
		                  <img src="" alt="">고객센터
		              </a>
		          </div>
		      </div>
		      <div id="header_3_2">
		          <form action="search.do" method="get" id="search_form">
		              <div id="search_text">
		                  <i class="fa-solid fa-magnifying-glass" style="color: #ffde59;"></i><input type="text" name="query">
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
		        <li>
		            <a href="<%= contextPath %>/selectRecipeList.re?currentPage=1">레시피</a>
		        </li>
		        <li>
		            <a href="">셰프</a>
		        </li>
		        <li>
		            <a href="<%= contextPath %>/main.po">마켓</a>
		        </li>
			</ul>
		</div>
	</div>
	
</nav>


<script>
	$(function(){
		$('#managerCheck').click(function(){
			const managerCheck = prompt('관리자 암호를 입력하세요');
			if(managerCheck == '1234' ){
				//console.log(managerCheck);
				return true;
			}
			else {
				//window.alert('관리자 암호가 일치하지 않습니다.');
				Swal.fire("관리자 페이지 접근 실패", "관리자 암호가 일치하지 않습니다", "warning");
				return false;
			}
		});
	})


</script>
</html>