<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.semi.member.model.vo.Member" %>
<%
	// session에서 관리자 정보 가져오기	
	Member loginMember = (Member)session.getAttribute("loginMember");
	// 성공 / 메시지
	String alertMsg =(String)session.getAttribute("alertMsg");

	String contextPath = request.getContextPath();
%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReSee:Recipe 관리자화면</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- font-awesome -->
<script src="https://kit.fontawesome.com/f74c934ec8.js" crossorigin="anonymous"></script>

<!-- sweetalert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>	

<link rel="stylesheet" href="<%= contextPath %>/resources/css/manager.css">
<script src="resources/js/manager/navbar_manager.js"></script>
<!-- css파일 따로 뺴서 link -->
<style>

</style>
</head>

<body>

	<!-- 관리자메뉴 title 상단 -->
	<div class="rs-title">
		<div class="titleMenu" id="recipeHome">
			<a href="<%= contextPath %>"><i class="fa-solid fa-house"></i></a>
			<span>관리자 메뉴</span>
		</div>
 		<div class="titleMenu" id="adminsetting" >
			<a href="<%=contextPath %>/hlsettingmanager.ma?adno=<%= loginMember.getMemNo() %>">
				<i class="fa-solid fa-gear"></i>
				<input type="hidden" name="HL_managerNo" value="<%= loginMember.getMemNo() %>">
				<img src="" alt="">정보 설정
			</a>
		</div>
	</div>
	
	<!-- 카테고리 리스트 -->
	<div class="rs-main">

		<div class="rs-navbar">
			<!-- 게시판 관리 카테고리 -->
			<div class="nav-item">
				<a href="#" id="HL_memberManage">게시판 관리</a>
			</div>
			<div class="category" id="HL_boardList">
				<ul>
					<li><a href="#" id="HL_NoticeManage" onclick="goMenu('/hlnoticemanage.no?cnpage=1');">공지사항 게시판</a></li>
					<li><a href="#">레시피 게시판</a></li>
					<li><a href="#">셰프 게시판</a></li>
				</ul>
			</div>
			<!-- 회원 관리 카테고리 -->
			<div class="nav-item">
				<a href="#" id="HL_memberManage">회원 관리</a>
			</div>
			<div class="category" id="HL_memberList">
				<ul>
					<li><a href="#" id="HL_memberSetting" onclick="goMenu('/hlmembermanage.ma?cmpage=1');">회원정보 관리</a></li>
					<li><a href="#">블랙리스트 관리</a></li>
					<li><a href="#">탈퇴회원 관리</a></li>
				</ul>
			</div>
			<!-- 주문 관리 카테고리 -->
			<div class="nav-item" >
				<a href="#">상품/주문 관리</a>
			</div>
			<div class="category">
				<ul>
					<li><a href="#">상품 등록</a></li>
					<li><a href="#">상품 조회/수정/삭제</a></li>
					<li><a href="#">주문 관리</a></li>
				</ul>
			</div>
			<!-- 문의 관리 카테고리 -->
			<div class="nav-item">
				<a href="#">문의 관리</a>
			</div>
			<div class="category">
				<ul>
					<li onclick="goMenu('/jhselect.dm');"><a href="#">쪽지함 관리</a></li>
					<li onclick="goMenu('/jhselect.rp?page=1');"><a href="#">신고함 관리</a></li>
				</ul>
			</div>
			<!-- 메뉴 관리 카테고리 -->
			<div class="nav-item">
				<a href="#">메뉴 관리</a>
			</div>
			<div class="category">
				<ul>
					<li onclick="goMenu('/jhselect.ct?page=1');"><a href="#">카테고리 관리</a></li>
					<li onclick="goMenu('/hsselect.hs?cpage=1');"><a href="#">해시태그 관리</a></li>
				</ul>
			</div>
			<!-- 리워드 관리 카테고리 -->
			<div class="nav-item">
				<a href="#">리워드 관리</a>
			</div>
			<div class="category">
				<ul>
					<li onclick="goMenu('/jhselect.rw?page=1');"><a href="#">리워드 지급/차감/조회</a></li>
					<li><a href="#">리워드 설정</a></li>
				</ul>
			</div>
			<!-- 쿠폰 관리 카테고리 -->
			<div class="nav-item">
				<a href="#">쿠폰 관리</a>
			</div>
			<div class="category">
				<ul>
					<li onclick="goMenu('/jhselect.cp?page=1');"><a href="#">쿠폰 등록/삭제</a></li>
					<li onclick="goMenu('/jhissue.cp?page=1');"><a href="#">쿠폰 발급/내역</a></li>
				</ul>
			</div>
		</div>	<!-- rs-navbar -->
			
		<!-- rs-content 추가된 영역 부분 -->
		
    </div>  <!-- rs-main -->
</body>


</html>