<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.semi.member.model.vo.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="resources/css/board/recipe_frag/recipeSortBar.css">
</head>


<body>
	
	<div id="recipe-sort-bar-wrap">

		<div id="recipe-sort-bar-img">
			<img src=".."><!--아이콘 이미지 삽입필요-->
		</div>
		
		<!-- 레시피 정렬 기준 -->
		<div class="recipe-sort-bar-menu">
			<input type="hidden" value="selectRecipeList">
			<h3 class="recipe-sort-by">최신순</h3>
		</div>
		<div class="recipe-sort-bar-menu">
			<input type="hidden" value="selectRecipeListHt">
			<h3 class="recipe-sort-by">좋아요순</h3>
		</div>
		<div class="recipe-sort-bar-menu">
			<input type="hidden" value="selectRecipeListVw">
			<h3 class="recipe-sort-by">조회수순</h3>
		</div>
		<div class="recipe-sort-bar-menu">
			<input type="hidden" value="selectRecipeListCh">
			<h3 class="recipe-sort-by">인기셰프순</h3>
		</div>
		
		<!-- 로그인 상태일 때만 글 작성 버튼 노출 -->
		<c:if test="${ sessionScope.loginMember ne null }">
			<div class="recipe-sort-bar-menu">
				<form action="recipeEnrollForm.re" method="post">
					<button type="submit">
						<h3 class="recipe-sort-by">글작성</h3>
					</button>
				</form>
			</div>
		</c:if>
		
		<!-- 레시피 키워드 검색창 -->
		<div id="recipe-search-area">
			<form action="searchKeyWord.re" method="get">
				<input type="hidden" value="recipeSearch">
				<input type="search" id="recipe-keyword-search-box" name="searchKeyWord" placeholder="　레시피 제목 / 작성자">
				<button type="submit" id="recipe-keyword-search-btn" class="fa fa-search btn"></button>
			</form>
		</div>
	</div>

	<script src="resources/js/board/recipe_frag/recipeSortBar.js"></script>
</body>
</html>